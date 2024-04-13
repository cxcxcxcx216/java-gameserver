package com.chen.action;

import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
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
    public void doAction(ProtoMsg msg, Session session){

    }

    @Override
    public void run() {
        doAction(msg,session);
    }
}
