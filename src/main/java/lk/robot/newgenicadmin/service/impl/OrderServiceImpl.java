package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.*;
import lk.robot.newgenicadmin.dto.request.ShipOrderRequestDTO;
import lk.robot.newgenicadmin.dto.response.OrderResponseDTO;
import lk.robot.newgenicadmin.entity.OrderDetailEntity;
import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.entity.PaymentEntity;
import lk.robot.newgenicadmin.enums.OrderStatus;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.OrderDetailRepository;
import lk.robot.newgenicadmin.repository.OrderRepository;
import lk.robot.newgenicadmin.repository.PaymentRepository;
import lk.robot.newgenicadmin.service.OrderService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public ResponseEntity<?> getOrders(String type) {
        try{
            String orderType = checkOrderType(type);
            if (orderType.equals(null)){
                return new ResponseEntity<>("Invalid order type",HttpStatus.BAD_REQUEST);
            }
            List<OrderEntity> orderList = orderRepository.findByStatus(orderType);
            if (!orderList.isEmpty()){
                List<OrderResponseDTO> responseList = new ArrayList<>();
                for (OrderEntity orderEntity :
                        orderList) {
                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                    List<OrderProductDTO> productDTOList = new ArrayList<>();
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        productDTOList.add(orderDetailEntityToDTO(orderDetailEntity));
                    }
                    OrderResponseDTO orderResponseDTO = orderEntityToDTO(orderEntity, productDTOList);
                    responseList.add(orderResponseDTO);
                }
                return new ResponseEntity<>(responseList,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("No orders yet", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> shipOrder(ShipOrderRequestDTO shipOrderRequestDTO) {
        try{
            if (!shipOrderRequestDTO.equals(null)){
                Optional<OrderEntity> order = orderRepository.findById(shipOrderRequestDTO.getOrderId());
                if (order.isPresent()){
                    if (order.get().getStatus() == OrderStatus.PENDING.toString()){
                        order.get().setStatus(OrderStatus.SHIPPED.toString());
                        order.get().setPickUpDate(DateConverter.localDateToSql(LocalDate.now()));
                        order.get().setPickUpTime(DateConverter.localTimeToSql(LocalTime.now()));
                        order.get().setTrackingNumber(shipOrderRequestDTO.getTrackingNumber());

                        OrderEntity save = orderRepository.save(order.get());
                        if (save.equals(null)){
                            return new ResponseEntity<>("Order not shipped",HttpStatus.BAD_REQUEST);
                        }
                        return new ResponseEntity<>("Order shipped",HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("No order ordered",HttpStatus.NOT_FOUND);
                    }
                }else {
                    return new ResponseEntity<>("Order not found",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Order id not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deliveredOrder(long orderId) {
        try{
            if (orderId != 0){
                Optional<OrderEntity> order = orderRepository.findById(orderId);
                if (order.isPresent() && (order.get().getStatus() == OrderStatus.SHIPPED.toString())){
                    order.get().setStatus(OrderStatus.DELIVERED.toString());
                    OrderEntity save = orderRepository.save(order.get());
                    if (save.equals(null)){
                        return new ResponseEntity<>("Order not delivered",HttpStatus.BAD_REQUEST);
                    }
                    paymentRepository.save(setPaymentDetails(order.get()));

                    return new ResponseEntity<>("Order delivered",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Order not found",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Order id not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private OrderProductDTO orderDetailEntityToDTO(OrderDetailEntity orderDetailEntity){

        OrderProductDTO orderProductDTO = modelMapper.map(orderDetailEntity.getProductEntity(), OrderProductDTO.class);
        orderProductDTO.setDiscount(orderDetailEntity.getProductEntity().getDealEntity().getDiscount());
        orderProductDTO.setQty(orderDetailEntity.getQuantity());
        orderProductDTO.setProductOrderPrice(orderDetailEntity.getOrderPrice());
        return orderProductDTO;
    }

    private OrderResponseDTO orderEntityToDTO(OrderEntity orderEntity,List<OrderProductDTO> productDTOList){
        OrderResponseDTO orderResponseDTO = modelMapper.map(orderEntity, OrderResponseDTO.class);
        orderResponseDTO.setUserDTO(modelMapper.map(orderEntity.getUserEntity(), UserDTO.class));
        orderResponseDTO.setDeliveryDTO(modelMapper.map(orderEntity.getDeliveryEntity(), DeliveryDTO.class));
        orderResponseDTO.setShippingDetails(modelMapper.map(orderEntity.getShippingDetails(), UserAddressDTO.class));
        orderResponseDTO.setShippingDetails(modelMapper.map(orderEntity.getBillingDetail(), UserAddressDTO.class));
        orderResponseDTO.setOrderPaymentDTO(modelMapper.map(orderEntity.getPaymentEntity(), OrderPaymentDTO.class));
        orderResponseDTO.setOrderProductDTOList(productDTOList);

        return orderResponseDTO;
    }

    private String checkOrderType(String type){
        switch (type){
            case "PENDING":
                return "PENDING";
            case "DELIVERED":
                return "DELIVERED";
            case "SHIPPED":
                return "SHIPPED";
            case "CANCELLED":
                return "CANCELLED";
            case "DELIVER_FAILED":
                return "DELIVER_FAILED";
        }
        return null;
    }

    private PaymentEntity setPaymentDetails(OrderEntity orderEntity){
        PaymentEntity paymentEntity = orderEntity.getPaymentEntity();
        paymentEntity.setPaymentDate(DateConverter.localDateToSql(LocalDate.now()));
        paymentEntity.setPaymentTime(DateConverter.localTimeToSql(LocalTime.now()));
        paymentEntity.setPaid(true);
        return paymentEntity;
    }
}
