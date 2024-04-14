package com.chen.action;

import com.chen.net.msg.ProtoMsg;
import com.chen.net.Session;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseAction implements Runnable{

    private Session session;
    private ProtoMsg msg;
    public void doAction(ProtoMsg msg, Session session) throws InvalidProtocolBufferException {

    }

    @Override
    public void run() {
        try {
            doAction(msg,session);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
