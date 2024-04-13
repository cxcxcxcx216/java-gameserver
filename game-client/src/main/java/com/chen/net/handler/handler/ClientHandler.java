package com.chen.net.handler.handler;


import com.chen.Application;
import com.chen.msg.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<ProtoMsg> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMsg protoMsg) throws Exception {
        log.info("服务器返回消息:{}",protoMsg);

        ProtoMsg protoMsg1 = new ProtoMsg();
        protoMsg1.setCode((short) 1001);
        ctx.writeAndFlush(protoMsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Application.ctx = ctx;
        ProtoMsg protoMsg1 = new ProtoMsg();
        protoMsg1.setCode((short) 1001);
        ctx.writeAndFlush(protoMsg1);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

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
