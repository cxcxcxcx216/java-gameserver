package com.chen.net.handler;

import com.chen.config.ProtocolConstants;
import com.chen.msg.ProtoMsg;
import com.chen.utils.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<ProtoMsg> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg msg, ByteBuf out) throws Exception {

    }
}
