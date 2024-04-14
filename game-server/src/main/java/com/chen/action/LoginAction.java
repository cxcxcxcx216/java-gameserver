package com.chen.action;


import com.chen.annotation.Action;
import com.chen.config.MsgCode;
import com.chen.config.ProcessorCode;
import com.chen.net.msg.ReqProtoMsg;
import com.chen.net.Session;
import com.chen.net.msg.ResProtoMsg;
import com.chen.proto.ProtoMsg;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.LoginAction,processorId = ProcessorCode.LoginProcessor,desc = "登录处理器")
@AllArgsConstructor
public class LoginAction extends BaseAction{

    @Override
    public ResProtoMsg doAction(ReqProtoMsg msg, Session session) throws InvalidProtocolBufferException {
        log.info("LoginAction 收到消息：{}",session.getCtx().channel().remoteAddress());
        //解析消息
        ProtoMsg.Person person = ProtoMsg.Person.parseFrom(msg.getData());

        log.info(person.toString());
        ResProtoMsg resp = new ResProtoMsg();
        resp.setMsgId(MsgCode.LoginAction);
        return resp;
    }
}
