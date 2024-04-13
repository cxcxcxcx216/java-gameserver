package com.chen.action;


import com.chen.annotation.Action;
import com.chen.config.MsgCode;
import com.chen.config.ProcessorCode;
import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.CreateRole,processorId = ProcessorCode.CreateRoleProcessor,desc = "角色处理器")
@AllArgsConstructor
public class CreateRoleAction extends BaseAction{
    @Override
    public void doAction(ProtoMsg msg, Session session) {
        log.info("创建角色");
        ProtoMsg protoMsg = new ProtoMsg();
        protoMsg.setMsgId((short) 1001);
        byte[] data = msg.getData();
        String str = new String(data);
        log.info("客户端发送的消息：{}",str);
        protoMsg.setData("角色创建成功".getBytes());
        session.getCtx().writeAndFlush(protoMsg);
    }
}
