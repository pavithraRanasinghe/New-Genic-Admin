package lk.robot.newgenicadmin.dto;

import lk.robot.newgenicadmin.entity.OrderEntity;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;

public class OrderPaymentDTO {
    private long paymentId;
    private double orderPrice;
    private double deliveryPrice;
    private double freeDeliveryPrice;
    private double discountPrice;
    private double refund;
    private Date paymentDate;
    private Time paymentTime;

    public OrderPaymentDTO() {
    }

    public OrderPaymentDTO(long paymentId,
                           double orderPrice,
                           double deliveryPrice,
                           double freeDeliveryPrice,
                           double discountPrice,
                           double refund,
                           Date paymentDate,
                           Time paymentTime) {
        this.paymentId = paymentId;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.freeDeliveryPrice = freeDeliveryPrice;
        this.discountPrice = discountPrice;
        this.refund = refund;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
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

    public double getFreeDeliveryPrice() {
        return freeDeliveryPrice;
    }

    public void setFreeDeliveryPrice(double freeDeliveryPrice) {
        this.freeDeliveryPrice = freeDeliveryPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return "OrderPaymentDTO{" +
                "paymentId=" + paymentId +
                ", orderPrice=" + orderPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", freeDeliveryPrice=" + freeDeliveryPrice +
                ", discountPrice=" + discountPrice +
                ", refund=" + refund +
                ", paymentDate=" + paymentDate +
                ", paymentTime=" + paymentTime +
                '}';
    }
}
