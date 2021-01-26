package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.*;
import lk.robot.newgenicadmin.dto.response.OrderResponseDTO;
import lk.robot.newgenicadmin.entity.OrderDetailEntity;
import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.enums.OrderStatus;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.OrderDetailRepository;
import lk.robot.newgenicadmin.repository.OrderRepository;
import lk.robot.newgenicadmin.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public ResponseEntity<?> getAllOrders() {
        try{
            List<OrderEntity> orderList = orderRepository.findByStatus(OrderStatus.PENDING.toString());
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

    private OrderProductDTO orderDetailEntityToDTO(OrderDetailEntity orderDetailEntity){

        OrderProductDTO orderProductDTO = modelMapper.map(orderDetailEntity.getProductEntity(), OrderProductDTO.class);
        orderProductDTO.setDiscount(orderDetailEntity.getProductEntity().getDealEntity().getDiscount());
        orderProductDTO.setQty(orderDetailEntity.getQuantity());
        orderProductDTO.setProductOrderPrice(orderDetailEntity.getOrderPrice());
        return orderProductDTO;
//        return new OrderProductDTO(
//                orderDetailEntity.getProductEntity().getProductId(),
//                orderDetailEntity.getProductEntity().getProductCode(),
//                orderDetailEntity.getProductEntity().getName(),
//                orderDetailEntity.getProductEntity().getDescription(),
//                orderDetailEntity.getProductEntity().getStock(),
//                orderDetailEntity.getProductEntity().getColor(),
//                orderDetailEntity.getProductEntity().getSize(),
//                orderDetailEntity.getProductEntity().getGender(),
//                orderDetailEntity.getProductEntity().getWeight(),
//                orderDetailEntity.getProductEntity().getSalePrice(),
//                orderDetailEntity.getProductEntity().getRetailPrice(),
//                orderDetailEntity.getProductEntity().isFreeShipping(),
//                orderDetailEntity.getProductEntity().getDealEntity().getDiscount(),
//                orderDetailEntity.getQuantity(),
//                orderDetailEntity.getOrderPrice()
//        );
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
}
