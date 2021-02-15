package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.ProductDTO;
import lk.robot.newgenicadmin.dto.ReorderPriceWeightDTO;
import lk.robot.newgenicadmin.dto.ReturnDetailDTO;
import lk.robot.newgenicadmin.dto.request.ReorderRequest;
import lk.robot.newgenicadmin.dto.response.ReturnResponseDTO;
import lk.robot.newgenicadmin.entity.*;
import lk.robot.newgenicadmin.enums.OrderStatus;
import lk.robot.newgenicadmin.enums.ReturnAction;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.*;
import lk.robot.newgenicadmin.service.ReturnService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.apache.tomcat.jni.Local;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ReturnServiceImpl implements ReturnService {

    private final ModelMapper modelMapper;
    private ReturnRepository returnRepository;
    private OrderDetailRepository orderDetailRepository;
    private ReturnDetailRepository returnDetailRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private DeliveryCostRepository deliveryCostRepository;

    @Autowired
    public ReturnServiceImpl(ModelMapper modelMapper,
                             ReturnRepository returnRepository,
                             OrderDetailRepository orderDetailRepository,
                             ReturnDetailRepository returnDetailRepository,
                             OrderRepository orderRepository,
                             PaymentRepository paymentRepository,
                             DeliveryCostRepository deliveryCostRepository) {
        this.modelMapper = modelMapper;
        this.returnRepository = returnRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.returnDetailRepository = returnDetailRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.deliveryCostRepository = deliveryCostRepository;
    }

    @Override
    public ResponseEntity<?> getReturnRequest() {
        try {
            List<ReturnEntity> returnEntityList = returnRepository.findByAction(ReturnAction.PENDING.toString());

            if (!returnEntityList.isEmpty()) {
                List<ReturnResponseDTO> returnResponseList = new ArrayList<>();
                for (ReturnEntity returnEntity :
                        returnEntityList) {
                    List<ReturnDetailEntity> returnDetails = returnDetailRepository.findByReturnEntity(returnEntity);
                    List<ReturnDetailDTO> returnDetailList = new ArrayList<>();
                    for (ReturnDetailEntity returnDetailEntity :
                            returnDetails) {
//                        returnDetailList.add(setProductDetail(returnDetailEntity));
                    }
                    returnResponseList.add(setReturnDetails(returnEntity, returnDetailList));
                }
                return new ResponseEntity<>(returnResponseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No return request", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> refundReturn(String orderId) {
        try {
            OrderEntity orderEntity = orderRepository.findByOrderUuid(orderId);
            if (orderEntity != null) {
                ReturnEntity returnEntity = returnRepository.findByOrderEntity(orderEntity);
                if (!returnEntity.equals(null)) {
                    PaymentEntity paymentEntity = returnEntity.getOrderEntity().getPaymentEntity();
                    double refund = calculateRefund(paymentEntity);
                    paymentEntity.setRefund(refund);
                    paymentEntity.setPaymentDate(DateConverter.localDateToSql(LocalDate.now()));
                    paymentEntity.setPaymentTime(DateConverter.localTimeToSql(LocalTime.now()));
                    paymentEntity.setPaid(true);
                    paymentRepository.save(paymentEntity);

                    returnEntity.setAction(ReturnAction.REFUND.toString());
                    returnEntity.setResponseDate(DateConverter.localDateToSql(LocalDate.now()));
                    returnEntity.setResponseTime(DateConverter.localTimeToSql(LocalTime.now()));

                    returnRepository.save(returnEntity);
                    return new ResponseEntity<>("Refund successful", HttpStatus.OK);

                } else {
                    return new ResponseEntity<>("No return found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> reorderReturn(ReorderRequest reorderRequest) {
        try {
            OrderEntity orderEntity = orderRepository.findByOrderUuid(reorderRequest.getOrderId());
            if (orderEntity != null) {
                ReturnEntity returnEntity = returnRepository.findByOrderEntity(orderEntity);
                if (!returnEntity.equals(null)) {
                    List<ReturnDetailEntity> returnDetails = returnDetailRepository.findByReturnEntity(returnEntity);
                    OrderEntity order = setNewOrder(orderEntity,
                            reorderRequest.getTrackingNumber(),
                            calculateWeightAndPrice(returnDetails, orderEntity));
                    OrderEntity newOrder = orderRepository.save(order);
                    if (!newOrder.equals(null)) {
                        for (ReturnDetailEntity returnDetailEntity :
                                returnDetails) {
                            OrderDetailEntity orderDetailEntity = setNewOrderDetail(returnDetailEntity, newOrder);
                            orderDetailRepository.save(orderDetailEntity);
                        }
                        return new ResponseEntity<>("ReOrder successful", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Order not completed", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("No return found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    private ReturnResponseDTO setReturnDetails(ReturnEntity returnEntity, List<ReturnDetailDTO> returnDetailList) {
        return new ReturnResponseDTO(
                returnEntity.getOrderEntity().getOrderUuid(),
                returnEntity.getOrderEntity().getOrderDate(),
                returnEntity.getOrderEntity().getOrderTime(),
                returnEntity.getRequestDate(),
                returnEntity.getRequestTime(),
                returnDetailList
        );
    }

//    private ReturnDetailDTO setProductDetail(ReturnDetailEntity returnDetail) {
//        return new ReturnDetailDTO(
//                returnDetail.getReason(),
//                returnDetail.getReturnQty(),
//                modelMapper.map(returnDetail.getOrderDetailEntity().getProductEntity(), ProductDTO.class)
//        );
//    }

    private double calculateRefund(PaymentEntity paymentEntity) {
        return paymentEntity.getOrderPrice() + paymentEntity.getDeliveryPrice();
    }

    private OrderEntity setNewOrder(OrderEntity orderDetail, String trackingNumber, ReorderPriceWeightDTO reorderPriceWeightDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderUuid(UUID.randomUUID().toString());
        orderEntity.setStatus(OrderStatus.SHIPPED.toString());
        orderEntity.setPickUpDate(DateConverter.localDateToSql(LocalDate.now()));
        orderEntity.setPickUpTime(DateConverter.localTimeToSql(LocalTime.now()));
        orderEntity.setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
        orderEntity.setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
        orderEntity.setTotalWeight(reorderPriceWeightDTO.getTotalWeight());
        orderEntity.setTrackingNumber(trackingNumber);
        orderEntity.setUserEntity(orderDetail.getUserEntity());
        orderEntity.setDeliveryEntity(orderDetail.getDeliveryEntity());
        orderEntity.setPaymentEntity(setPayment(reorderPriceWeightDTO));
        orderEntity.setShippingDetails(orderDetail.getShippingDetails());
        orderEntity.setBillingDetail(orderDetail.getBillingDetail());

        return orderEntity;
    }

    private OrderDetailEntity setNewOrderDetail(ReturnDetailEntity returnDetailEntity, OrderEntity orderEntity) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setQuantity(returnDetailEntity.getReturnQty());
        orderDetailEntity.setOrderPrice(returnDetailEntity.getOrderDetailEntity().getCombinationEntity().getRetailPrice() * returnDetailEntity.getReturnQty());
        orderDetailEntity.setCombinationEntity(returnDetailEntity.getOrderDetailEntity().getCombinationEntity());
        orderDetailEntity.setOrderEntity(orderEntity);
        return orderDetailEntity;
    }

    private PaymentEntity setPayment(ReorderPriceWeightDTO reorderPriceWeightDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setReorder(reorderPriceWeightDTO.getTotalPrice() + reorderPriceWeightDTO.getTotalPrice());
        paymentEntity.setPaymentDate(DateConverter.localDateToSql(LocalDate.now()));
        paymentEntity.setPaymentTime(DateConverter.localTimeToSql(LocalTime.now()));
        paymentEntity.setPaid(true);
        return paymentEntity;
    }

    private ReorderPriceWeightDTO calculateWeightAndPrice(List<ReturnDetailEntity> returnDetails, OrderEntity orderEntity) {
        String district = orderEntity.getShippingDetails().getDistrict();
        double totalWeight = 0;
        double totalPrice = 0;
        double deliveryPrice = 0;
        for (ReturnDetailEntity returnDetailEntity :
                returnDetails) {
            totalWeight += (returnDetailEntity.getReturnQty() * returnDetailEntity.getOrderDetailEntity().getCombinationEntity().getWeight());
            totalPrice += (returnDetailEntity.getReturnQty() * returnDetailEntity.getOrderDetailEntity().getCombinationEntity().getRetailPrice());
        }

        DeliveryCostEntity districtPrice = deliveryCostRepository.findByDistrictAndDeliveryEntity(district, orderEntity.getDeliveryEntity());

        if (totalWeight != 0) {
            deliveryPrice = districtPrice.getCost();
        }
        if (totalWeight > 1000) {
            int round = (int) Math.ceil(totalWeight / 1000);
            deliveryPrice += (districtPrice.getCostPerExtra() * round);
        }
        return new ReorderPriceWeightDTO(totalWeight, totalPrice, deliveryPrice);
    }
}
