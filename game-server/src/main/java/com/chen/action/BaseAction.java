package com.chen.action;

import com.chen.net.msg.ReqProtoMsg;
import com.chen.net.Session;
import com.chen.net.msg.ResProtoMsg;
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
    private ReqProtoMsg msg;
    public abstract ResProtoMsg doAction(ReqProtoMsg msg, Session session) throws Exception;

    @Override
    public void run() {
        try {
            ResProtoMsg resProtoMsg = doAction(msg, session);
            if (resProtoMsg != null){
                session.getCtx().writeAndFlush(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
