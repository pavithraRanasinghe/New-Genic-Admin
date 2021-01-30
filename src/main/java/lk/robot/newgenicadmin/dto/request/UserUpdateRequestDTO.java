package lk.robot.newgenicadmin.dto.request;

public class UserUpdateRequestDTO {

    private String firstName;
    private String lastName;
    private String gender;
    private String gmail;
    private String mobile;

    public UserUpdateRequestDTO() {
    }

    public UserUpdateRequestDTO(String firstName,
                                String lastName,
                                String gender,
                                String gmail,
                                String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.gmail = gmail;
        this.mobile = mobile;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "UserUpdateRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
