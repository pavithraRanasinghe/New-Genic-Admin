package lk.robot.newgenicadmin.dto;


public class OrderProductDTO {

    private long productId;
    private String productCode;
    private String name;
    private String description;
    private int stock;
    private String color;
    private String size;
    private String gender;
    private double weight;
    private double salePrice;
    private double retailPrice;
    private boolean isFreeShipping;
    private double discount;
    private int qty;
    private double productOrderPrice;

    public OrderProductDTO() {
    }

    public OrderProductDTO(long productId,
                           String productCode,
                           String name,
                           String description,
                           int stock,
                           String color,
                           String size,
                           String gender,
                           double weight,
                           double salePrice,
                           double retailPrice,
                           boolean isFreeShipping,
                           double discount,
                           int qty,
                           double productOrderPrice) {
        this.productId = productId;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.weight = weight;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.isFreeShipping = isFreeShipping;
        this.discount = discount;
        this.qty = qty;
        this.productOrderPrice = productOrderPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
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

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", isFreeShipping=" + isFreeShipping +
                ", discount=" + discount +
                ", qty=" + qty +
                ", productOrderPrice=" + productOrderPrice +
                '}';
    }
}
