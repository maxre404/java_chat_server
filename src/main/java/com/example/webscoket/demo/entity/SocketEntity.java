package com.example.webscoket.demo.entity;

/**
 *
 */
public class SocketEntity {
    private int cmd;//命令
    private int type;//聊天类型，0群聊，1单聊；
    private String groupId;//如果是群聊 则有这个id
    private String fromUser;//发送者
    private String toUser;//接收者；（可以是用户名等）session.getId（）
    private String message;//消息
    private String imgUrl;//用户头像地址 目前只有群聊用到了
    public int getType() {
        return type;
    }

    public void setType( int type ) {
        this.type=type;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser( String fromUser ) {
        this.fromUser=fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser( String toUser ) {
        this.toUser=toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message=message;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "SocketEntity{" +
                "cmd=" + cmd +
                ", type=" + type +
                ", groupId='" + groupId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", message='" + message + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
