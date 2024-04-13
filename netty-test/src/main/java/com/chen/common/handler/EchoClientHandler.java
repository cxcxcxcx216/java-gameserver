package com.chen.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 当连接建立时发送消息给服务器
        ByteBuf msg = ctx.alloc().buffer().writeBytes("Hello, Server!".getBytes());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 接收并打印服务器回传的消息
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println("Received: " + in.toString());
        } finally {
            in.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }
}
