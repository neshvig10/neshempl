package com.neshempl.usermanagement.dto;



public class LoginRequest {

    private Long userPhone;

    private String userPassword;

    private String userRole;

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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LoginRequest(Long userPhone, String userPassword, String userRole) {
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
