package lk.robot.newgenicadmin.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "return_request")
public class ReturnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_request_id")
    private long returnRequestId;
    @Column(name = "request_date")
    private Date requestDate;
    @Column(name = "request_time")
    private Time requestTime;
    @Column
    private String action;
    @OneToOne(mappedBy = "returnEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private OrderEntity orderEntity;

    public ReturnEntity() {
    }

    public ReturnEntity(long returnRequestId,
                        Date requestDate,
                        Time requestTime,
                        String action,
                        OrderEntity orderEntity) {
        this.returnRequestId = returnRequestId;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.orderEntity = orderEntity;
        this.action = action;
        this.orderEntity = orderEntity;
    }

    public long getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(long returnRequestId) {
        this.returnRequestId = returnRequestId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Time getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Time requestTime) {
        this.requestTime = requestTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }


    @Override
    public String toString() {
        return "ReturnEntity{" +
                "returnRequestId=" + returnRequestId +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                ", action='" + action + '\'' +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
