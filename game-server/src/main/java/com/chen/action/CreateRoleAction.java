package com.chen.action;


import com.chen.annotation.Action;
import com.chen.config.MsgCode;
import com.chen.config.ProcessorCode;
import com.chen.net.msg.ProtoMsg;
import com.chen.net.Session;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.CreateRole,processorId = ProcessorCode.CreateRoleProcessor,desc = "角色处理器")
@AllArgsConstructor
public class CreateRoleAction extends BaseAction{
    @Override
    public void doAction(ProtoMsg msg, Session session) throws InvalidProtocolBufferException {
        byte[] data = msg.getData();
        com.chen.proto.ProtoMsg.Person person = com.chen.proto.ProtoMsg.Person.parseFrom(data);
        log.info("person-{}-{}",person.getId(),person.getName());
    }
}
