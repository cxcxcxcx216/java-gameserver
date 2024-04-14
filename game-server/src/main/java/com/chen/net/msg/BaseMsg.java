package com.chen.net.msg;


import com.chen.proto.ProtoMsg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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

    //解析消息
    public Object getBody() {
        Object object = null;
        try {
            object = convertToObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private static Object convertToObject(byte[] byteArray) throws IOException, ClassNotFoundException {
        // 创建一个字节数组输入流，将字节数组包装起来
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);

        // 创建对象输入流，从字节数组输入流中读取对象
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);

        // 读取对象并返回
        Object object = objectInputStream.readObject();

        // 关闭输入流
        objectInputStream.close();

        return object;
    }
}