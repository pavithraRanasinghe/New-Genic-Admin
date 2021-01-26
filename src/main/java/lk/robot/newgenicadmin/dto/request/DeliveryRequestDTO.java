package lk.robot.newgenicadmin.dto.request;


import lk.robot.newgenicadmin.dto.response.DeliveryCostDTO;

public class DeliveryRequestDTO {

    private String name;
    private String address;
    private String gmail;
    private String mobile;
    private String webSite;
    private String regNo;
    private DeliveryCostDTO deliveryCostDTO;

    public DeliveryRequestDTO() {
    }

    public DeliveryRequestDTO(String name,
                              String address,
                              String gmail,
                              String mobile,
                              String webSite,
                              String regNo,
                              DeliveryCostDTO deliveryCostDTO) {
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.mobile = mobile;
        this.webSite = webSite;
        this.regNo = regNo;
        this.deliveryCostDTO = deliveryCostDTO;
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

    public DeliveryCostDTO getDeliveryCostDTO() {
        return deliveryCostDTO;
    }

    public void setDeliveryCostDTO(DeliveryCostDTO deliveryCostDTO) {
        this.deliveryCostDTO = deliveryCostDTO;
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
                ", deliveryCostDTO=" + deliveryCostDTO +
                '}';
    }
}
