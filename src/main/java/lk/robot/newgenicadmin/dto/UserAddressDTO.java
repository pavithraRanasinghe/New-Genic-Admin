package lk.robot.newgenicadmin.dto;

public class UserAddressDTO {

    private long userAddress_id;
    private String firstName;
    private String lastName;
    private String address;
    private String district;
    private String city;
    private String mobile;
    private int postalCode;

    public UserAddressDTO() {
    }

    public UserAddressDTO(long userAddress_id,
                          String firstName,
                          String lastName,
                          String address,
                          String district,
                          String city,
                          String mobile,
                          int postalCode) {
        this.userAddress_id = userAddress_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.district = district;
        this.city = city;
        this.mobile = mobile;
        this.postalCode = postalCode;
    }

    public long getUserAddress_id() {
        return userAddress_id;
    }

    public void setUserAddress_id(long userAddress_id) {
        this.userAddress_id = userAddress_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "UserAddressDTO{" +
                "userAddress_id=" + userAddress_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
