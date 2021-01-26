package lk.robot.newgenicadmin.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_code",nullable = false)
    private String productCode;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String brand;
    @Column
    private int stock;
    @Column
    private String color;
    @Column
    private String size;
    @Column
    private String gender;
    @Column
    private Double weight;
    @Column(name = "buying_price")
    private double buyingPrice;
    @Column(name = "sale_price")
    private double salePrice;
    @Column(name = "retail_price")
    private double retailPrice;
    @Column(name = "added_date",nullable = false)
    private Date addedDate;
    @Column(name = "added_time",nullable = false)
    private Time addedTime;
    @Column(nullable = false)
    private boolean active;
    @Column(name = "free_shipping")
    private boolean freeShipping;

    @ManyToOne
    @JoinColumn(name = "fk_sub_category_id")
    private SubCategoryEntity subCategoryEntity;

    @ManyToOne
    @JoinColumn(name = "fk_deal_id")
    private DealEntity dealEntity;

    public ProductEntity() {
    }

    public ProductEntity(
            long productId,
            String productCode,
            String name,
            String description,
            String brand,
            int stock,
            String color,
            String size,
            String gender,
            double weight,
            double buyingPrice,
            double salePrice,
            double retailPrice,
            Date addedDate,
            Time addedTime,
            boolean active,
            boolean freeShipping,
            SubCategoryEntity subCategoryEntity,
            DealEntity dealEntity) {
        this.productId = productId;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.stock = stock;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.weight = weight;
        this.buyingPrice = buyingPrice;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.addedDate = addedDate;
        this.addedTime = addedTime;
        this.active = active;
        this.freeShipping = freeShipping;
        this.subCategoryEntity = subCategoryEntity;
        this.dealEntity = dealEntity;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Time getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Time addedTime) {
        this.addedTime = addedTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public SubCategoryEntity getSubCategoryEntity() {
        return subCategoryEntity;
    }

    public void setSubCategoryEntity(SubCategoryEntity subCategoryEntity) {
        this.subCategoryEntity = subCategoryEntity;
    }

    public DealEntity getDealEntity() {
        return dealEntity;
    }

    public void setDealEntity(DealEntity dealEntity) {
        this.dealEntity = dealEntity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", buyingPrice=" + buyingPrice +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", addedDate=" + addedDate +
                ", addedTime=" + addedTime +
                ", active=" + active +
                ", freeShipping=" + freeShipping +
                ", subCategoryEntity=" + subCategoryEntity +
                ", dealEntity=" + dealEntity +
                '}';
    }
}
