package com.chen.common.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            // 将接收到的消息写回客户端
            ctx.write(in);
        } finally {
            // 释放接收到的消息
            in.release();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // 将缓冲区中的消息写入 SocketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理
        cause.printStackTrace();
        ctx.close();
    }
}