package lk.robot.newgenicadmin.dto;

import java.sql.Date;
import java.sql.Time;

public class ReturnDetailDTO {
    private String reason;
    private int requestQty;
    private ProductDTO productDTO;

    public ReturnDetailDTO() {
    }

    public ReturnDetailDTO(String reason,
                           int requestQty,
                           ProductDTO productDTO) {
        this.reason = reason;
        this.requestQty = requestQty;
        this.productDTO = productDTO;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getRequestQty() {
        return requestQty;
    }

    public void setRequestQty(int requestQty) {
        this.requestQty = requestQty;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "ReturnDetailDTO{" +
                ", reason='" + reason + '\'' +
                ", requestQty=" + requestQty +
                ", productDTO=" + productDTO +
                '}';
    }
}
