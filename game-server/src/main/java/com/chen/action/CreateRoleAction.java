package com.chen.action;


import com.chen.annotation.Action;
import com.chen.config.MsgCode;
import com.chen.config.ProcessorCode;
import com.chen.net.msg.ReqProtoMsg;
import com.chen.net.Session;
import com.chen.net.msg.ResProtoMsg;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Action(msgId = MsgCode.CreateRole,processorId = ProcessorCode.CreateRoleProcessor,desc = "角色处理器")
@AllArgsConstructor
public class CreateRoleAction extends BaseAction{
    @Override
    public ResProtoMsg doAction(ReqProtoMsg msg, Session session) throws InvalidProtocolBufferException {
        byte[] data = msg.getData();
        com.chen.proto.ProtoMsg.Person person = com.chen.proto.ProtoMsg.Person.parseFrom(data);
        log.info("person-{}-{}",person.getId(),person.getName());
        return null;
    }
}
