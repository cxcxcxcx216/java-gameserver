package com.chen.net.handler;


import com.chen.Application;
import com.chen.msg.ProtoMsg;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<String> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String protoMsg) throws Exception {
        log.info("服务器返回消息:{}",protoMsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Application.ctx = ctx;
        ctx.writeAndFlush("1231231");
        log.info("客户端向服务器发送消息");
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
