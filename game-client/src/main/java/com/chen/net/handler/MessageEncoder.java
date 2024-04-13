package com.chen.net.handler;

import com.chen.msg.ProtoMsg;
import com.chen.utils.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<ProtoMsg> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg msg, ByteBuf out) {
        //获取消息体的长度
        int length = msg.getData().length;

        //获取消息号
        short messageId = msg.getMsgId();
        //消息体
        byte[] data = msg.getData();

        //写入消息体长度
        out.writeShort(BitUtils.SwapInt16((short) length));

        //写入消息号
        out.writeShort(BitUtils.SwapInt16(messageId));

        //写入消息体具体数据
        out.writeBytes(data);
    }
}
