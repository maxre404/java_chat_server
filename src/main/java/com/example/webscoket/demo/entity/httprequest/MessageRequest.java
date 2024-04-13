package com.example.webscoket.demo.entity.httprequest;

public class MessageRequest {
    private int type;
    private String groupId;
    private String fromUser;
    private String toUser;

    public MessageRequest(int type, String groupId, String fromUser, String toUser) {
        this.type = type;
        this.groupId = groupId;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "type=" + type +
                ", groupId='" + groupId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                '}';
    }
}
