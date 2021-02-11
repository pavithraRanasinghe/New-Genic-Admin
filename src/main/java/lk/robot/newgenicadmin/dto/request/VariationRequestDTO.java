package lk.robot.newgenicadmin.dto.request;

import java.util.List;

public class VariationRequestDTO {

    private long variationId;
    private String variationName;
    private String variationDescription;
    private List<String> valueList;

    public VariationRequestDTO() {
    }

    public VariationRequestDTO(long variationId,
                               String variationName,
                               String variationDescription,
                               List<String> valueList) {
        this.variationId = variationId;
        this.variationName = variationName;
        this.variationDescription = variationDescription;
        this.valueList = valueList;
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

    public String getVariationDescription() {
        return variationDescription;
    }

    public void setVariationDescription(String variationDescription) {
        this.variationDescription = variationDescription;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "VariationRequestDTO{" +
                "variationId=" + variationId +
                ", variationName='" + variationName + '\'' +
                ", variationDescription='" + variationDescription + '\'' +
                ", valueList=" + valueList +
                '}';
    }
}
