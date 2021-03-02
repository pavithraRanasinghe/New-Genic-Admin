package lk.robot.newgenicadmin.dto.request;


import lk.robot.newgenicadmin.dto.DeliveryCostDTO;

import java.util.List;

public class DeliveryRequestDTO {

    private String name;
    private String address;
    private String gmail;
    private String mobile;
    private String webSite;
    private String regNo;
    private List<DeliveryCostRequestDTO> deliveryCostList;

    public DeliveryRequestDTO() {
    }

    public DeliveryRequestDTO(String name,
                              String address,
                              String gmail,
                              String mobile,
                              String webSite,
                              String regNo,
                              List<DeliveryCostRequestDTO> deliveryCostList) {
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.mobile = mobile;
        this.webSite = webSite;
        this.regNo = regNo;
        this.deliveryCostList = deliveryCostList;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public List<DeliveryCostRequestDTO> getDeliveryCostList() {
        return deliveryCostList;
    }

    public void setDeliveryCostList(List<DeliveryCostRequestDTO> deliveryCostList) {
        this.deliveryCostList = deliveryCostList;
    }

    @Override
    public String toString() {
        return "DeliveryRequestDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", webSite='" + webSite + '\'' +
                ", regNo='" + regNo + '\'' +
                ", deliveryCostList=" + deliveryCostList +
                '}';
    }
}
