package com.example.webscoket.demo.entity;

public class SocketUser {
    private int type;//聊天类型，0群聊，1单聊；
    private long groupId;//群Id
    private String userId;
    private String name;
    private String sessionId;
    private String imgUrl;

    public SocketUser(int type, long groupId, String userId, String name, String sessionId, String imgUrl) {
        this.type = type;
        this.groupId = groupId;
        this.userId = userId;
        this.name = name;
        this.sessionId = sessionId;
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
