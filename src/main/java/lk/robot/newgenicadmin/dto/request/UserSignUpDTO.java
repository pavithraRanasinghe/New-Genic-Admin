package lk.robot.newgenicadmin.dto.request;

import javax.validation.constraints.*;

public class UserSignUpDTO {
    // TODO: 2/8/21 this class must be remove
    private String firstName;
    private String lastName;
    private String userName;
    @NotEmpty(message = "Email cannot be null or empty")
    @NotNull(message = "Email cannot be null or empty")
    @Email(message = "Please input valid email address")
    private String gmail;
    @Pattern(regexp = "[0-9]{9,10}",message = "Please input valid mobile number")
    private String mobile;
    private String dob;
    @Size(min = 7, message = "Password should have 8 characters")
    @NotEmpty(message = "Password cannot be null or empty")
    @NotNull(message = "Password cannot be null or empty")
    private String password;
    private String profilePicture;
    private String role;

    public UserSignUpDTO(String firstName,
                         String lastName,
                         String userName,
                         String gmail,
                         String mobile,
                         String dob,
                         String password,
                         String profilePicture,
                         String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.password = password;
        this.profilePicture = profilePicture;
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSignUpDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
