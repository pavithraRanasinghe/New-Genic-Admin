package lk.robot.newgenicadmin.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "product_feedback")
public class ProductFeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_feedback_id")
    private long productFeedbackId;
    @Column(nullable = false,length = 4)
    private int rate;
    @Column
    private String message;
    @Column(name = "added_date")
    private Date addedDate;
    @Column(name = "added_time")
    private Time addedTime;
    @Column(name = "is_approved")
    private boolean isApproved;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public ProductFeedbackEntity() {
    }

    public ProductFeedbackEntity(long productFeedbackId,
                                 int rate,
                                 String message,
                                 Date addedDate,
                                 Time addedTime,
                                 boolean isApproved,
                                 UserEntity userEntity,
                                 ProductEntity productEntity) {
        this.productFeedbackId = productFeedbackId;
        this.rate = rate;
        this.message = message;
        this.addedDate = addedDate;
        this.addedTime = addedTime;
        this.isApproved = isApproved;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    public long getProductFeedbackId() {
        return productFeedbackId;
    }

    public void setProductFeedbackId(long productFeedbackId) {
        this.productFeedbackId = productFeedbackId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "ProductFeedbackEntity{" +
                "productFeedbackId=" + productFeedbackId +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                ", addedDate=" + addedDate +
                ", addedTime=" + addedTime +
                ", isApproved=" + isApproved +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
