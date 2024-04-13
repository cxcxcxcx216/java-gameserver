package com.chen.entity;


import com.chen.annotation.Processor;
import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {

    private int pid;

    private Session session;


    public void sendMsg(ProtoMsg protoMsg){
        session.getCtx().writeAndFlush(protoMsg);
    }

}
