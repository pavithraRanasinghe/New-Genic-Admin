package lk.robot.newgenicadmin.dto;

public class VariationDetailDTO {
    private long variationDetailId;
    private String value;

    public VariationDetailDTO() {
    }

    public VariationDetailDTO(long variationDetailId,
                              String value) {
        this.variationDetailId = variationDetailId;
        this.value = value;
    }

    public long getVariationDetailId() {
        return variationDetailId;
    }

    public void setVariationDetailId(long variationDetailId) {
        this.variationDetailId = variationDetailId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VariationDetailDTO{" +
                "variationDetailId=" + variationDetailId +
                ", value='" + value + '\'' +
                '}';
    }
}
