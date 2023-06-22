package com.example.appbanhangonline.models;

public class User {
    private int userID;
    private String username;
    private String numberPhone;
    private String address;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int userID, String username, String numberPhone, String address, String email, String password, String role) {
        this.userID = userID;
        this.username = username;
        this.numberPhone = numberPhone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
