package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.CombinationDTO;

public class ProductResponseDTO {

    private String uuid;
    private String productCode;
    private String name;
    private String description;
    private String brand;
    private boolean freeShipping;
    private double discount;
    private int qty;
    private double productOrderPrice;
    private CombinationDTO combinationDTO;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(String uuid,
                              String productCode,
                              String name,
                              String description,
                              String brand,
                              boolean freeShipping,
                              double discount,
                              int qty,
                              double productOrderPrice,
                              CombinationDTO combinationDTO) {
        this.uuid = uuid;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.freeShipping = freeShipping;
        this.discount = discount;
        this.qty = qty;
        this.productOrderPrice = productOrderPrice;
        this.combinationDTO = combinationDTO;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getProductOrderPrice() {
        return productOrderPrice;
    }

    public void setProductOrderPrice(double productOrderPrice) {
        this.productOrderPrice = productOrderPrice;
    }

    public CombinationDTO getCombinationDTO() {
        return combinationDTO;
    }

    public void setCombinationDTO(CombinationDTO combinationDTO) {
        this.combinationDTO = combinationDTO;
    }

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "uuid='" + uuid + '\'' +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", freeShipping=" + freeShipping +
                ", discount=" + discount +
                ", qty=" + qty +
                ", productOrderPrice=" + productOrderPrice +
                ", combinationDTO=" + combinationDTO +
                '}';
    }
}
