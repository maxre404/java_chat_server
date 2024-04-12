package com.example.webscoket.demo.entity;

public class SocketUser {
    private String userId;
    private String name;
    private String sessionId;
    private String imgUrl;

    public SocketUser(String userId,String name, String sessionId,String imgUrl) {
        this.userId = userId;
        this.name = name;
        this.sessionId = sessionId;
        this.imgUrl = imgUrl;
    }
}
