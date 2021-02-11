package lk.robot.newgenicadmin.dto.request;

import java.util.List;

public class ProductImageRequestDTO {

    private long variationId;
    private String value;

    public ProductImageRequestDTO() {
    }

    public ProductImageRequestDTO(long variationId,
                                  String value) {
        this.variationId = variationId;
        this.value = value;
    }

    public long getVariationId() {
        return variationId;
    }

    public void setVariationId(long variationId) {
        this.variationId = variationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductImageRequestDTO{" +
                "variationId=" + variationId +
                ", value='" + value + '\'' +
                '}';
    }
}
