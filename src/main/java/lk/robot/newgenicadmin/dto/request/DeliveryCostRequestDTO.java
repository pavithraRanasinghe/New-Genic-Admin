package lk.robot.newgenicadmin.dto.request;

public class DeliveryCostRequestDTO {
    private String district;
    private double cost;
    private double costPerExtra;

    public DeliveryCostRequestDTO() {
    }

    public DeliveryCostRequestDTO(String district,
                                  double cost,
                                  double costPerExtra) {
        this.district = district;
        this.cost = cost;
        this.costPerExtra = costPerExtra;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCostPerExtra() {
        return costPerExtra;
    }

    public void setCostPerExtra(double costPerExtra) {
        this.costPerExtra = costPerExtra;
    }

    @Override
    public String toString() {
        return "DeliveryCostDTO{" +
                ", district='" + district + '\'' +
                ", cost=" + cost +
                ", costPerExtra=" + costPerExtra +
                '}';
    }
}
