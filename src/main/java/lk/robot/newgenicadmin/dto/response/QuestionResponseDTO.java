package lk.robot.newgenicadmin.dto.response;

import java.sql.Date;
import java.sql.Time;

public class QuestionResponseDTO {

    private long questionId;
    private String question;
    private Date questionDate;
    private Time questionTime;
    private String userId;
    private String userName;
    private String productId;

    public QuestionResponseDTO() {
    }

    public QuestionResponseDTO(long questionId,
                               String question,
                               Date questionDate,
                               Time questionTime,
                               String userId,
                               String userName,
                               String productId) {
        this.questionId = questionId;
        this.question = question;
        this.questionDate = questionDate;
        this.questionTime = questionTime;
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Time getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Time questionTime) {
        this.questionTime = questionTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "QuestionResponseDTO{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", questionDate=" + questionDate +
                ", questionTime=" + questionTime +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
