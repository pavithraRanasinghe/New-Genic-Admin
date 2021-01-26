package lk.robot.newgenicadmin.dto.response;

import java.util.List;

public class MainSubCategoryResponseDTO {
    private long main_sub_category_id;
    private String main_sub_category_name;
    private String main_sub_category_description;
    private List<SubCategoryResponseDTO> subCategoryResponseDTO;

    public MainSubCategoryResponseDTO() {
    }

    public MainSubCategoryResponseDTO(long main_sub_category_id,
                                      String main_sub_category_name,
                                      String main_sub_category_description,
                                      List<SubCategoryResponseDTO> subCategoryResponseDTO) {
        this.main_sub_category_id = main_sub_category_id;
        this.main_sub_category_name = main_sub_category_name;
        this.main_sub_category_description = main_sub_category_description;
        this.subCategoryResponseDTO = subCategoryResponseDTO;
    }

    public long getMain_sub_category_id() {
        return main_sub_category_id;
    }

    public void setMain_sub_category_id(long main_sub_category_id) {
        this.main_sub_category_id = main_sub_category_id;
    }

    public String getMain_sub_category_name() {
        return main_sub_category_name;
    }

    public void setMain_sub_category_name(String main_sub_category_name) {
        this.main_sub_category_name = main_sub_category_name;
    }

    public String getMain_sub_category_description() {
        return main_sub_category_description;
    }

    public void setMain_sub_category_description(String main_sub_category_description) {
        this.main_sub_category_description = main_sub_category_description;
    }

    public List<SubCategoryResponseDTO> getSubCategoryResponseDTO() {
        return subCategoryResponseDTO;
    }

    public void setSubCategoryResponseDTO(List<SubCategoryResponseDTO> subCategoryResponseDTO) {
        this.subCategoryResponseDTO = subCategoryResponseDTO;
    }

    @Override
    public String toString() {
        return "MainSubCategoryResponseDTO{" +
                "main_sub_category_id=" + main_sub_category_id +
                ", main_sub_category_name='" + main_sub_category_name + '\'' +
                ", main_sub_category_description='" + main_sub_category_description + '\'' +
                ", subCategoryResponseDTO=" + subCategoryResponseDTO +
                '}';
    }
}
