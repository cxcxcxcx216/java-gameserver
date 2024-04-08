package com.chen.action;


import com.chen.annotation.Action;
import com.chen.entity.Player;
import com.chen.msg.ProtoMsg;

@Action(msgId = 1001,processorId = 101,desc = "登录处理器")
public class LoginAction extends BaseAction<Player>{

    @Override
    public void doAction(ProtoMsg msg, Player player) {

    }
}
