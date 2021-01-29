package lk.robot.newgenicadmin.dto.response;

import lk.robot.newgenicadmin.dto.DeliveryCostDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DeliveryResponseDTO {

    private long deliveryId;
    private String name;
    private String address;
    private String gmail;
    private String mobile;
    private String website;
    private Date registrationDate;
    private Time registrationTime;
    private String registrationNumber;
    private boolean active;
    private List<DeliveryCostDTO> deliveryCostDTOList;

    public DeliveryResponseDTO() {
    }

    public DeliveryResponseDTO(long deliveryId,
                               String name,
                               String address,
                               String gmail,
                               String mobile,
                               String website,
                               Date registrationDate,
                               Time registrationTime,
                               String registrationNumber,
                               boolean active,
                               List<DeliveryCostDTO> deliveryCostDTOList) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.mobile = mobile;
        this.website = website;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.registrationNumber = registrationNumber;
        this.active = active;
        this.deliveryCostDTOList = deliveryCostDTOList;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Time getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Time registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<DeliveryCostDTO> getDeliveryCostDTOList() {
        return deliveryCostDTOList;
    }

    public void setDeliveryCostDTOList(List<DeliveryCostDTO> deliveryCostDTOList) {
        this.deliveryCostDTOList = deliveryCostDTOList;
    }

    @Override
    public String toString() {
        return "DeliveryResponseDTO{" +
                "deliveryId=" + deliveryId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", website='" + website + '\'' +
                ", registrationDate=" + registrationDate +
                ", registrationTime=" + registrationTime +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", active=" + active +
                ", deliveryCostDTOList=" + deliveryCostDTOList +
                '}';
    }
}
