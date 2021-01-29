package lk.robot.newgenicadmin.dto;

public class ReorderPriceWeightDTO {

    private double totalWeight;
    private double totalPrice;
    private double deliveryPrice;

    public ReorderPriceWeightDTO() {
    }

    public ReorderPriceWeightDTO(double totalWeight,
                                 double totalPrice,
                                 double deliveryPrice) {
        this.totalWeight = totalWeight;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {
        return "ReorderPriceWeightDTO{" +
                "totalWeight=" + totalWeight +
                ", totalPrice=" + totalPrice +
                ", deliveryPrice=" + deliveryPrice +
                '}';
    }
}

