package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.ProductDTO;
import lk.robot.newgenicadmin.dto.ReturnDetailDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ReturnResponseDTO {


    private String orderId;
    private Date orderDate;
    private Time orderTime;
    private Date requestDate;
    private Time requestTime;
    private List<ReturnDetailDTO> returnDetailDTOList;

    public ReturnResponseDTO() {
    }

    public ReturnResponseDTO(String orderId,
                             Date orderDate,
                             Time orderTime,
                             Date requestDate,
                             Time requestTime,
                             List<ReturnDetailDTO> returnDetailDTOList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.returnDetailDTOList = returnDetailDTOList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
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

    public List<ReturnDetailDTO> getReturnDetailDTOList() {
        return returnDetailDTOList;
    }

    public void setReturnDetailDTOList(List<ReturnDetailDTO> returnDetailDTOList) {
        this.returnDetailDTOList = returnDetailDTOList;
    }

    @Override
    public String toString() {
        return "ReturnResponseDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                ", returnDetailDTOList=" + returnDetailDTOList +
                '}';
    }
}
