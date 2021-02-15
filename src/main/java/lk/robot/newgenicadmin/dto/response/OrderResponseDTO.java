package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.*;
import lk.robot.newgenicadmin.entity.*;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrderResponseDTO {

    private String orderId;
    private Date orderDate;
    private Time orderTime;
    private double totalWeight;
    private String trackingNumber;
    private Date pickUpDate;
    private Time pickUpTime;
    private UserDTO userDTO;
    private DeliveryDTO deliveryDTO;
    private UserAddressDTO shippingDetails;
    private UserAddressDTO billingDetail;
    private List<ProductResponseDTO> orderProductDTOList;
    private OrderPaymentDTO orderPaymentDTO;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(String orderId,
                            Date orderDate,
                            Time orderTime,
                            double totalWeight,
                            String trackingNumber,
                            Date pickUpDate,
                            Time pickUpTime,
                            UserDTO userDTO,
                            DeliveryDTO deliveryDTO,
                            UserAddressDTO shippingDetails,
                            UserAddressDTO billingDetail,
                            List<ProductResponseDTO> orderProductDTOList,
                            OrderPaymentDTO orderPaymentDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalWeight = totalWeight;
        this.trackingNumber = trackingNumber;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.userDTO = userDTO;
        this.deliveryDTO = deliveryDTO;
        this.shippingDetails = shippingDetails;
        this.billingDetail = billingDetail;
        this.orderProductDTOList = orderProductDTOList;
        this.orderPaymentDTO = orderPaymentDTO;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Time getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Time pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public DeliveryDTO getDeliveryDTO() {
        return deliveryDTO;
    }

    public void setDeliveryDTO(DeliveryDTO deliveryDTO) {
        this.deliveryDTO = deliveryDTO;
    }

    public UserAddressDTO getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(UserAddressDTO shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public UserAddressDTO getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(UserAddressDTO billingDetail) {
        this.billingDetail = billingDetail;
    }

    public List<ProductResponseDTO> getOrderProductDTOList() {
        return orderProductDTOList;
    }

    public void setOrderProductDTOList(List<ProductResponseDTO> orderProductDTOList) {
        this.orderProductDTOList = orderProductDTOList;
    }

    public OrderPaymentDTO getOrderPaymentDTO() {
        return orderPaymentDTO;
    }

    public void setOrderPaymentDTO(OrderPaymentDTO orderPaymentDTO) {
        this.orderPaymentDTO = orderPaymentDTO;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", totalWeight=" + totalWeight +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", userDTO=" + userDTO +
                ", deliveryDTO=" + deliveryDTO +
                ", shippingDetails=" + shippingDetails +
                ", billingDetail=" + billingDetail +
                ", orderProductDTOList=" + orderProductDTOList +
                ", orderPaymentDTO=" + orderPaymentDTO +
                '}';
    }
}
