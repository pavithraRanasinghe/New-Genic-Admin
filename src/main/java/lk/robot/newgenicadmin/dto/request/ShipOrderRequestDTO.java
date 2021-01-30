package lk.robot.newgenicadmin.dto.request;

public class ShipOrderRequestDTO {

    private long orderId;
    private String trackingNumber;

    public ShipOrderRequestDTO() {
    }

    public ShipOrderRequestDTO(long orderId,
                               String trackingNumber) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return "ShipOrderRequestDTO{" +
                "orderId=" + orderId +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
