package com.chen.action;


import com.chen.annotation.Action;
import com.chen.config.MsgCode;
import com.chen.config.ProcessorCode;
import com.chen.net.msg.ProtoMsg;
import com.chen.net.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.LoginAction,processorId = ProcessorCode.LoginProcessor,desc = "登录处理器")
@AllArgsConstructor
public class LoginAction extends BaseAction{

    @Override
    public void doAction(ProtoMsg msg, Session session) {
        log.info("LoginAction 收到消息：{}",session.getCtx().channel().remoteAddress());
        byte[] data = msg.getData();
        String str = new String(data);
        log.info(str);
    }
}
