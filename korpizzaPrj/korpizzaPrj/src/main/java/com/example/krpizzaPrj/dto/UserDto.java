package com.example.krpizzaPrj.dto;

public class UserDto {
    private int userNum;
    private String userId;
    private String userEmail;
    private String userName;
    private String userPasswd;
    private String userRegDate;
    private String userEndDate;
    private String userSt;


    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

    public String getUserEndDate() {
        return userEndDate;
    }

    public void setUserEndDate(String userEndDate) {
        this.userEndDate = userEndDate;
    }

    public String getUserSt() {
        return userSt;
    }

    public void setUserSt(String userSt) {
        this.userSt = userSt;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userNum=" + userNum +
                ", userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userRegDate='" + userRegDate + '\'' +
                ", userEndDate='" + userEndDate + '\'' +
                ", userSt='" + userSt + '\'' +
                '}';
    }
}

