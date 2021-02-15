package lk.robot.newgenicadmin.entity;

import javax.persistence.*;

@Entity
@Table(name = "variation")
public class VariationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variation_id")
    private long variationId;
    @Column(name = "variation_name")
    private String variationName;
    @Column(name = "variation_description")
    private String variationDescription;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public VariationEntity() {
    }

    public VariationEntity(long variationId,
                           String variationName,
                           String variationDescription,
                           ProductEntity productEntity) {
        this.variationId = variationId;
        this.variationName = variationName;
        this.variationDescription = variationDescription;
        this.productEntity = productEntity;
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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "VariationEntity{" +
                "variationId=" + variationId +
                ", variationName='" + variationName + '\'' +
                ", variationDescription='" + variationDescription + '\'' +
                ", productEntity=" + productEntity +
                '}';
    }
}
