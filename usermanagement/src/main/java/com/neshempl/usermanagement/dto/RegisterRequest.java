package com.neshempl.usermanagement.dto;

public class RegisterRequest {

    private String userName;

    private Long userPhone;

    private String userPassword;

    private String userEmail;

    private String userRole;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public RegisterRequest(String userName, Long userPhone, String userPassword, String userEmail, String userRoles) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRoles;
    }
}
