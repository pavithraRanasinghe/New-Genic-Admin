package lk.robot.newgenicadmin.dto.response;

public class FinanceResponseDTO {

    private double orderPrices;
    private double deliveryPrices;
    private double freeDeliveryPrices;
    private double discounts;
    private double refund;
    private double reorder;
    private double profit;

    public FinanceResponseDTO() {
    }

    public FinanceResponseDTO(double orderPrices,
                              double deliveryPrices,
                              double freeDeliveryPrices,
                              double discounts,
                              double refund,
                              double reorder,
                              double profit) {
        this.orderPrices = orderPrices;
        this.deliveryPrices = deliveryPrices;
        this.freeDeliveryPrices = freeDeliveryPrices;
        this.discounts = discounts;
        this.refund = refund;
        this.reorder = reorder;
        this.profit = profit;
    }

    public double getOrderPrices() {
        return orderPrices;
    }

    public void setOrderPrices(double orderPrices) {
        this.orderPrices = orderPrices;
    }

    public double getDeliveryPrices() {
        return deliveryPrices;
    }

    public void setDeliveryPrices(double deliveryPrices) {
        this.deliveryPrices = deliveryPrices;
    }

    public double getFreeDeliveryPrices() {
        return freeDeliveryPrices;
    }

    public void setFreeDeliveryPrices(double freeDeliveryPrices) {
        this.freeDeliveryPrices = freeDeliveryPrices;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public double getReorder() {
        return reorder;
    }

    public void setReorder(double reorder) {
        this.reorder = reorder;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "FinanceResponseDTO{" +
                "orderPrices=" + orderPrices +
                ", deliveryPrices=" + deliveryPrices +
                ", freeDeliveryPrices=" + freeDeliveryPrices +
                ", discounts=" + discounts +
                ", refund=" + refund +
                ", reorder=" + reorder +
                ", profit=" + profit +
                '}';
    }
}
