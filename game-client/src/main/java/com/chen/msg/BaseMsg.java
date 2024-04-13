package com.chen.msg;


public class BaseMsg {
    private short msgId;
    private byte[] data;

    public BaseMsg() {
    }


    public short getMsgId() {
        return msgId;
    }

    public void setMsgId(short msgId) {
        this.msgId = msgId;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}