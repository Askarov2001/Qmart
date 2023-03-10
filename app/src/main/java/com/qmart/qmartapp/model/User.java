package com.qmart.qmartapp.model;

public class User {

    String userName, emailID, userPassword;

    public User(String userName, String emailID, String userPassword){
        this.userName = userName;
        this.emailID = emailID;
        this.userPassword = userPassword;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User() {

    }


}
