package com.example.webscoket.demo.entity;

public class SocketPackage {
    private int cmd;
    private Object data;

    public SocketPackage(int cmd, Object data) {
        this.cmd = cmd;
        this.data = data;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
