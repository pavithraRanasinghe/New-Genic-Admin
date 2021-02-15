package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.VariationDetailDTO;

import java.util.List;

public class CombinationResponseDTO {

    private long combinationId;
    private List<VariationDetailDTO> variationList;

    public CombinationResponseDTO() {
    }

    public CombinationResponseDTO(long combinationId,
                                  List<VariationDetailDTO> variationList) {
        this.combinationId = combinationId;
        this.variationList = variationList;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public List<VariationDetailDTO> getVariationList() {
        return variationList;
    }

    public void setVariationList(List<VariationDetailDTO> variationList) {
        this.variationList = variationList;
    }

    @Override
    public String toString() {
        return "CombinationResponseDTO{" +
                "combinationId=" + combinationId +
                ", variationList=" + variationList +
                '}';
    }
}
