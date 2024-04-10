package com.example.webscoket.demo.entity;

public class SocketUser {
    private String name;
    private String sessionId;
    private String imgUrl;

    public SocketUser(String name, String sessionId,String imgUrl) {
        this.name = name;
        this.sessionId = sessionId;
        this.imgUrl = imgUrl;
    }
}
