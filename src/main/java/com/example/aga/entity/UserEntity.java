package com.example.aga.entity;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "user")
public class UserEntity {

    private String userName;
    private String userEmail;
    private String userPassword;
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
//    private String registerTime;  //会员注册时间,默认为空
//    private String isMember;     //是否是会员，默认不是
//    private String overDate;      //会员过期时间，默认为空
//    private String isoverDate;      //指示会员是否过期，默认为是

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

//    public String getRegisterTime() {
//        return registerTime;
//    }
//
//    public void setRegisterTime(String registerTime) {
//        this.registerTime = registerTime;
//    }
//
//    public String getIsMember() {
//        return isMember;
//    }
//
//    public void setIsMember(String isMember) {
//        this.isMember = isMember;
//    }
//
//    public String getOverDate() {
//        return overDate;
//    }
//
//    public void setOverDate(String overDate) {
//        this.overDate = overDate;
//    }
//
//    public String getIsoverDate() {
//        return isoverDate;
//    }
//
//    public void setIsoverDate(String isoverDate) {
//        this.isoverDate = isoverDate;
//    }
}
