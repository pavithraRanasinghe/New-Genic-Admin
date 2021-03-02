package lk.robot.newgenicadmin.dto;

import lk.robot.newgenicadmin.dto.response.ProductResponseDTO;

import java.sql.Date;
import java.sql.Time;

public class ReturnDetailDTO {
    private String reason;
    private int requestQty;
    private ProductResponseDTO productResponseDTO;

    public ReturnDetailDTO() {
    }

    public ReturnDetailDTO(String reason,
                           int requestQty,
                           ProductResponseDTO productResponseDTO) {
        this.reason = reason;
        this.requestQty = requestQty;
        this.productResponseDTO = productResponseDTO;
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

    public ProductResponseDTO getProductResponseDTO() {
        return productResponseDTO;
    }

    public void setProductResponseDTO(ProductResponseDTO productResponseDTO) {
        this.productResponseDTO = productResponseDTO;
    }

    @Override
    public String toString() {
        return "ReturnDetailDTO{" +
                "reason='" + reason + '\'' +
                ", requestQty=" + requestQty +
                ", productResponseDTO=" + productResponseDTO +
                '}';
    }
}
