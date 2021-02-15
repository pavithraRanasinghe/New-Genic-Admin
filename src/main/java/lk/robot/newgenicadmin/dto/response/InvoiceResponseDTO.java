package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.ProductDTO;
import lk.robot.newgenicadmin.dto.UserAddressDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class InvoiceResponseDTO {

    private String orderId;
    private Date orderDate;
    private Time orderTime;
    private String trackingNumber;
    private double orderPrice;
    private double deliveryPrice;
    private double discountPrice;
    private double totalPrice;
    private UserAddressDTO billingDetails;
    private UserAddressDTO shippingDetails;
    List<ProductResponseDTO> productDTOList;

    public InvoiceResponseDTO() {
    }

    public InvoiceResponseDTO(String orderId,
                              Date orderDate,
                              Time orderTime,
                              String trackingNumber,
                              double orderPrice,
                              double deliveryPrice,
                              double discountPrice,
                              double totalPrice,
                              UserAddressDTO billingDetails,
                              UserAddressDTO shippingDetails,
                              List<ProductResponseDTO> productDTOList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.trackingNumber = trackingNumber;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.discountPrice = discountPrice;
        this.totalPrice = totalPrice;
        this.billingDetails = billingDetails;
        this.shippingDetails = shippingDetails;
        this.productDTOList = productDTOList;
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserAddressDTO getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(UserAddressDTO billingDetails) {
        this.billingDetails = billingDetails;
    }

    public UserAddressDTO getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(UserAddressDTO shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public List<ProductResponseDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductResponseDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    @Override
    public String toString() {
        return "InvoiceResponseDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", orderPrice=" + orderPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", discountPrice=" + discountPrice +
                ", totalPrice=" + totalPrice +
                ", billingDetails=" + billingDetails +
                ", shippingDetails=" + shippingDetails +
                ", productDTOList=" + productDTOList +
                '}';
    }
}
