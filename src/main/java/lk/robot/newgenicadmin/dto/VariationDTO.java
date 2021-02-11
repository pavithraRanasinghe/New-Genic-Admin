package lk.robot.newgenicadmin.dto;

public class VariationDTO {

    private long variationId;
    private String variationName;
    private long VariationDetailId;
    private String value;

    public VariationDTO() {
    }

    public VariationDTO(long variationId,
                        String variationName,
                        long variationDetailId,
                        String value) {
        this.variationId = variationId;
        this.variationName = variationName;
        VariationDetailId = variationDetailId;
        this.value = value;
    }

    public long getVariationId() {
        return variationId;
    }

    public void setVariationId(long variationId) {
        this.variationId = variationId;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public long getVariationDetailId() {
        return VariationDetailId;
    }

    public void setVariationDetailId(long variationDetailId) {
        VariationDetailId = variationDetailId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VariationDTO{" +
                "variationId=" + variationId +
                ", variationName='" + variationName + '\'' +
                ", VariationDetailId=" + VariationDetailId +
                ", value='" + value + '\'' +
                '}';
    }
}
