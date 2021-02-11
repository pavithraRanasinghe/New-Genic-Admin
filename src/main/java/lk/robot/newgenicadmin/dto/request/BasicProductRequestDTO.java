package lk.robot.newgenicadmin.dto.request;

public class BasicProductRequestDTO {
    private String productCode;
    private String name;
    private String description;
    private long subCategoryId;
    private String brand;
    private boolean active;
    private boolean isFreeShipping;

    public BasicProductRequestDTO() {
    }

    public BasicProductRequestDTO(String productCode,
                                  String name,
                                  String description,
                                  long subCategoryId,
                                  String brand,
                                  boolean active,
                                  boolean isFreeShipping) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.subCategoryId = subCategoryId;
        this.brand = brand;
        this.active = active;
        this.isFreeShipping = isFreeShipping;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    @Override
    public String toString() {
        return "BasicProductRequestDTO{" +
                "productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", brand='" + brand + '\'' +
                ", active=" + active +
                ", isFreeShipping=" + isFreeShipping +
                '}';
    }
}
