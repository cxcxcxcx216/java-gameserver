package com.chen.net.handler;


import com.chen.ApplicationClient;
import com.chen.config.MsgCode;
import com.chen.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;


@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<ProtoMsg> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMsg protoMsg) {
        log.info("服务器返回消息:{}",protoMsg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ApplicationClient.ctx = ctx;
//        ProtoMsg protoMsg = new ProtoMsg();
//        protoMsg.setMsgId(MsgCode.LoginAction);
//        String message = "hello, server";
//        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);;
//        protoMsg.setData(bytes);
//        ctx.writeAndFlush(protoMsg);
//        log.info("客户端向服务器发送消息->{}",bytes.length);

        com.chen.proto.ProtoMsg.Person.Builder person = com.chen.proto.ProtoMsg.Person.newBuilder();
        com.chen.proto.ProtoMsg.Person person1 = person.setId(101).addEmail("123123").setName("chenxing").build();
        com.chen.msg.ProtoMsg protoMsg = new com.chen.msg.ProtoMsg();


        protoMsg.setMsgId(MsgCode.CreateRole);
        protoMsg.setData(person1.toByteArray());

        ctx.writeAndFlush(protoMsg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务器断开链接");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }
}
