package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.response.FinanceResponseDTO;
import lk.robot.newgenicadmin.entity.OrderDetailEntity;
import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.entity.PaymentEntity;
import lk.robot.newgenicadmin.enums.OrderStatus;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.OrderDetailRepository;
import lk.robot.newgenicadmin.repository.OrderRepository;
import lk.robot.newgenicadmin.repository.PaymentRepository;
import lk.robot.newgenicadmin.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public FinanceServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository,
                              OrderDetailRepository orderDetailRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public ResponseEntity<?> findProfit() {
        try{
            List<OrderEntity> orderList = orderRepository.findByStatus(OrderStatus.DELIVERED.toString());
            if (!orderList.isEmpty()){
                FinanceResponseDTO financeResponseDTO = new FinanceResponseDTO();
                for (OrderEntity orderEntity:
                     orderList) {
                    financeResponseDTO.setOrderPrices(financeResponseDTO.getOrderPrices() + orderEntity.getPaymentEntity().getOrderPrice());
                    financeResponseDTO.setDeliveryPrices(financeResponseDTO.getDeliveryPrices() + orderEntity.getPaymentEntity().getDeliveryPrice());
                    financeResponseDTO.setDiscounts(financeResponseDTO.getDiscounts() + orderEntity.getPaymentEntity().getDiscountPrice());
                    financeResponseDTO.setRefund(financeResponseDTO.getRefund() + orderEntity.getPaymentEntity().getRefund());
                    financeResponseDTO.setReorder(financeResponseDTO.getReorder() + orderEntity.getPaymentEntity().getReorder());
                    financeResponseDTO.setFreeDeliveryPrices(financeResponseDTO.getFreeDeliveryPrices() + orderEntity.getPaymentEntity().getFreeDeliveryPrice());

                    double buyingPrice = orderEntity.getPaymentEntity().getBuyingPrice();
                    double profit = (orderEntity.getPaymentEntity().getOrderPrice() - buyingPrice) -
                            (orderEntity.getPaymentEntity().getRefund() + orderEntity.getPaymentEntity().getReorder());
                    financeResponseDTO.setProfit(financeResponseDTO.getProfit() + profit);
                }
                return new ResponseEntity<>(financeResponseDTO, HttpStatus.OK);

            }else {
                return new ResponseEntity<>("No orders found", HttpStatus.BAD_GATEWAY);
            }
        }catch (Exception e){
            throw new CustomException("Failed to find profit");
        }
    }

    private double findBuyingPrice(OrderEntity orderEntity){
        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
        double orderBuyingPrice = 0;
        if (!orderDetailList.isEmpty()){
            for (OrderDetailEntity orderDetailEntity :
                    orderDetailList) {
                orderBuyingPrice = orderDetailEntity.getCombinationEntity().getBuyingPrice() * orderDetailEntity.getQuantity();
            }
        }
        return orderBuyingPrice;
    }
}
