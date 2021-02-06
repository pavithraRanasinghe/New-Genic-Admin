package lk.robot.newgenicadmin.dto.request;

public class ReorderRequest {

    private String orderId;
    private String trackingNumber;

    public ReorderRequest() {
    }

    public ReorderRequest(String orderId,
                          String trackingNumber) {
        this.orderId = orderId;
        this.trackingNumber = trackingNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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
        return "ReorderRequest{" +
                "orderId=" + orderId +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
