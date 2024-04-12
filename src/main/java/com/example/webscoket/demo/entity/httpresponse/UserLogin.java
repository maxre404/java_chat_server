package com.example.webscoket.demo.entity.httpresponse;

public class UserLogin {
    private String userName;
    private String userId;
    private String imgUrl;//头像地址

    public UserLogin(String userName, String userId,String imgUrl) {
        this.userName = userName;
        this.userId = userId;
        this.imgUrl = imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
