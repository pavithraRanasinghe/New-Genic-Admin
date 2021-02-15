package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.*;
import lk.robot.newgenicadmin.dto.request.ShipOrderRequestDTO;
import lk.robot.newgenicadmin.dto.response.InvoiceResponseDTO;
import lk.robot.newgenicadmin.dto.response.OrderResponseDTO;
import lk.robot.newgenicadmin.dto.response.ProductResponseDTO;
import lk.robot.newgenicadmin.dto.response.VariationDTO;
import lk.robot.newgenicadmin.entity.*;
import lk.robot.newgenicadmin.enums.OrderStatus;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.*;
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
    private VariationCombinationDetailRepository variationCombinationDetailRepository;
    private VariationRepository variationRepository;
    private VariationDetailRepository variationDetailRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            VariationCombinationDetailRepository variationCombinationDetailRepository,
                            VariationRepository variationRepository,
                            VariationDetailRepository variationDetailRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
        this.variationRepository = variationRepository;
        this.variationDetailRepository = variationDetailRepository;
    }

    @Override
    public ResponseEntity<?> getOrders(String type) {
        try {
            String orderType = checkOrderType(type);
            if (orderType.equals(null)) {
                return new ResponseEntity<>("Invalid order type", HttpStatus.BAD_REQUEST);
            }
            List<OrderEntity> orderList = orderRepository.findByStatus(orderType);
            if (!orderList.isEmpty()) {
                List<OrderResponseDTO> responseList = new ArrayList<>();
                for (OrderEntity orderEntity :
                        orderList) {
                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                    List<ProductResponseDTO> productDTOList = new ArrayList<>();
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        productDTOList.add(orderDetailEntityToDTO(orderDetailEntity));
                    }
                    OrderResponseDTO orderResponseDTO = orderEntityToDTO(orderEntity, productDTOList);
                    responseList.add(orderResponseDTO);
                }
                return new ResponseEntity<>(responseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No orders yet", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> shipOrder(ShipOrderRequestDTO shipOrderRequestDTO) {
        try {
            if (!shipOrderRequestDTO.equals(null)) {
                OrderEntity order = orderRepository.findByOrderUuid(shipOrderRequestDTO.getOrderId());
                if (order != null) {
                    if (order.getStatus() == OrderStatus.PENDING.toString()) {
                        order.setStatus(OrderStatus.SHIPPED.toString());
                        order.setPickUpDate(DateConverter.localDateToSql(LocalDate.now()));
                        order.setPickUpTime(DateConverter.localTimeToSql(LocalTime.now()));
                        order.setTrackingNumber(shipOrderRequestDTO.getTrackingNumber());

                        OrderEntity save = orderRepository.save(order);
                        if (save.equals(null)) {
                            return new ResponseEntity<>("Order not shipped", HttpStatus.BAD_REQUEST);
                        }
                        return new ResponseEntity<>("Order shipped", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("No order ordered", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Order id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deliveredOrder(String orderId) {
        try {
            if (orderId != null) {
                OrderEntity order = orderRepository.findByOrderUuid(orderId);
                if (order != null && (order.getStatus() == OrderStatus.SHIPPED.toString())) {
                    order.setStatus(OrderStatus.DELIVERED.toString());
                    OrderEntity save = orderRepository.save(order);
                    if (save.equals(null)) {
                        return new ResponseEntity<>("Order not delivered", HttpStatus.BAD_REQUEST);
                    }
                    paymentRepository.save(setPaymentDetails(order));

                    return new ResponseEntity<>("Order delivered", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Order not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Order id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> printInvoice(String orderId) {
        try {
            OrderEntity order = orderRepository.findByOrderUuid(orderId);
            if (order != null) {
                InvoiceResponseDTO invoiceResponseDTO = setInvoiceDetail(order);
                if (invoiceResponseDTO.equals(null)){
                    return new ResponseEntity<>("Invoice details not found",HttpStatus.BAD_GATEWAY);
                }
                return new ResponseEntity<>(invoiceResponseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found", HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to print invoice");
        }
    }

    private ProductResponseDTO orderDetailEntityToDTO(OrderDetailEntity orderDetailEntity) {

        ProductResponseDTO productResponseDTO = setProductDetails(getProductFromCombination(orderDetailEntity.getCombinationEntity()));
        productResponseDTO.setDiscount(getProductFromCombination(orderDetailEntity.getCombinationEntity()).getDealEntity().getDiscount());
        productResponseDTO.setQty(orderDetailEntity.getQuantity());
        productResponseDTO.setProductOrderPrice(orderDetailEntity.getOrderPrice());
        return productResponseDTO;
    }

    private OrderResponseDTO orderEntityToDTO(OrderEntity orderEntity, List<ProductResponseDTO> productDTOList) {
        OrderResponseDTO orderResponseDTO = modelMapper.map(orderEntity, OrderResponseDTO.class);
        orderResponseDTO.setUserDTO(modelMapper.map(orderEntity.getUserEntity(), UserDTO.class));
        orderResponseDTO.setDeliveryDTO(modelMapper.map(orderEntity.getDeliveryEntity(), DeliveryDTO.class));
        orderResponseDTO.setShippingDetails(modelMapper.map(orderEntity.getShippingDetails(), UserAddressDTO.class));
        orderResponseDTO.setShippingDetails(modelMapper.map(orderEntity.getBillingDetail(), UserAddressDTO.class));
        orderResponseDTO.setOrderPaymentDTO(modelMapper.map(orderEntity.getPaymentEntity(), OrderPaymentDTO.class));
        orderResponseDTO.setOrderProductDTOList(productDTOList);

        return orderResponseDTO;
    }

    private String checkOrderType(String type) {
        switch (type) {
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

    private PaymentEntity setPaymentDetails(OrderEntity orderEntity) {
        PaymentEntity paymentEntity = orderEntity.getPaymentEntity();
        paymentEntity.setPaymentDate(DateConverter.localDateToSql(LocalDate.now()));
        paymentEntity.setPaymentTime(DateConverter.localTimeToSql(LocalTime.now()));
        paymentEntity.setPaid(true);
        return paymentEntity;
    }

    private InvoiceResponseDTO setInvoiceDetail(OrderEntity order) {
        return new InvoiceResponseDTO(
                order.getOrderUuid(),
                order.getOrderDate(),
                order.getOrderTime(),
                order.getTrackingNumber(),
                order.getPaymentEntity().getOrderPrice(),
                order.getPaymentEntity().getDeliveryPrice(),
                order.getPaymentEntity().getDiscountPrice(),
                (order.getPaymentEntity().getOrderPrice() + order.getPaymentEntity().getDeliveryPrice()) - order.getPaymentEntity().getDiscountPrice(),
                modelMapper.map(order.getBillingDetail(),UserAddressDTO.class),
                modelMapper.map(order.getShippingDetails(),UserAddressDTO.class),
                setProductDetails(order));
    }

    private List<ProductResponseDTO> setProductDetails(OrderEntity orderEntity){
        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
        List<ProductResponseDTO> productList = new ArrayList<>();
        for (OrderDetailEntity orderDetailEntity :
                orderDetailList) {
            ProductEntity product = getProductFromCombination(orderDetailEntity.getCombinationEntity());
            ProductResponseDTO productResponseDTO = setProductDetails(product);
            productList.add(productResponseDTO);
        }
        return productList;
    }

    private ProductEntity getProductFromCombination(CombinationEntity combinationEntity) {
        List<VariationCombinationDetailEntity> variationCombinationList = variationCombinationDetailRepository.findByCombinationEntity(combinationEntity);
        if (!variationCombinationList.isEmpty()) {
            ProductEntity productEntity = new ProductEntity();
            for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                    variationCombinationList) {
                productEntity = variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getProductEntity();
            }
            return productEntity;
        } else {
            return null;
        }
    }

    private ProductResponseDTO setProductDetails(ProductEntity productEntity) {
        List<CombinationDTO> combinationList = new ArrayList<>();
        List<VariationEntity> variationList = variationRepository.findByProductEntity(productEntity);
        for (VariationEntity variationEntity :
                variationList) {
            List<VariationDetailEntity> byVariationEntity = variationDetailRepository.findByVariationEntity(variationEntity);
            for (VariationDetailEntity variationDetailEntity :
                    byVariationEntity) {

                List<VariationCombinationDetailEntity> variationCombinationDetailList = variationCombinationDetailRepository.findByVariationDetailEntity(variationDetailEntity);

                for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                        variationCombinationDetailList) {
                    CombinationEntity combinationEntity = variationCombinationDetailEntity.getCombinationEntity();

                    CombinationDTO combinationDTO = new CombinationDTO();
                    combinationDTO.setCombinationId(combinationEntity.getCombinationId());
                    combinationDTO.setStock(combinationEntity.getStock());
                    combinationDTO.setWeight(combinationEntity.getWeight());
                    combinationDTO.setSalePrice(combinationEntity.getSalePrice());
                    combinationDTO.setRetailPrice(combinationEntity.getRetailPrice());

                    VariationDTO variationDTO = new VariationDTO();
                    VariationDetailEntity variationDetail = variationCombinationDetailEntity.getVariationDetailEntity();
                    variationDTO.setVariationDetailId(variationDetail.getVariationDetailId());
                    variationDTO.setValue(variationDetail.getValue());
                    variationDTO.setVariationId(variationDetail.getVariationEntity().getVariationId());
                    variationDTO.setVariationName(variationDetail.getVariationEntity().getVariationName());

                    if (combinationList.isEmpty()){
                        combinationList.add(combinationDTO);
                    }

                    for (CombinationDTO combination :
                            combinationList) {
                        if (combination.getCombinationId() == combinationEntity.getCombinationId()){
                            combination.getVariationList().add(variationDTO);
                        }else{
                            List<VariationDTO> allVariationList = new ArrayList<>();
                            allVariationList.add(variationDTO);
                            combinationDTO.setVariationList(allVariationList);
                            combinationList.add(combinationDTO);
                        }
                    }
                }
            }
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setUuid(productEntity.getUuid());
        productResponseDTO.setName(productEntity.getName());
        productResponseDTO.setDescription(productEntity.getDescription());
        productResponseDTO.setBrand(productEntity.getBrand());
        productResponseDTO.setFreeShipping(productEntity.isFreeShipping());
        productResponseDTO.setVariationList(combinationList);

        return productResponseDTO;
    }
}
