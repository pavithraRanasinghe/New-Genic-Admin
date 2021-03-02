package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.entity.UserEntity;

import java.sql.Date;
import java.sql.Time;

public class ProductFeedbackResponseDTO {

    private long productFeedbackId;
    private int rate;
    private String message;
    private Date addedDate;
    private Time addedTime;
    private boolean isApproved;
    private String userFirstName;

    public ProductFeedbackResponseDTO() {
    }

    public ProductFeedbackResponseDTO(long productFeedbackId,
                                      int rate,
                                      String message,
                                      Date addedDate,
                                      Time addedTime,
                                      boolean isApproved,
                                      String userFirstName) {
        this.productFeedbackId = productFeedbackId;
        this.rate = rate;
        this.message = message;
        this.addedDate = addedDate;
        this.addedTime = addedTime;
        this.isApproved = isApproved;
        this.userFirstName = userFirstName;
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Override
    public String toString() {
        return "ProductFeedbackResponseDTO{" +
                "productFeedbackId=" + productFeedbackId +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                ", addedDate=" + addedDate +
                ", addedTime=" + addedTime +
                ", isApproved=" + isApproved +
                ", userFirstName='" + userFirstName + '\'' +
                '}';
    }
}
