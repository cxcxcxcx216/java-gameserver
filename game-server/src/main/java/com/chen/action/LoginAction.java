package com.chen.action;


import com.chen.annotation.Action;
import com.chen.entity.Player;
import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
@Action(msgId = 1001,processorId = 101,desc = "登录处理器")
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
