package com.chen.net.handler;

import com.chen.net.msg.ReqProtoMsg;
import com.chen.utils.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> out) {

        final int readableBytes = msg.readableBytes();
        if (readableBytes < 2) {
            return;
        }

        msg.markReaderIndex();

        //解析消息体长度
        int dataLength = BitUtils.SwapInt16(msg.readShort());//消息体长度


        //解析消息号
        if (!msg.isReadable(2)) {//数据不够长，重置位置
            msg.resetReaderIndex();
            return;
        }
        short messageId = BitUtils.SwapInt16(msg.readShort());//消息Id

        // 标记当前读取位置
        msg.markReaderIndex();

        // 检查是否有足够的字节可读取
        if (!msg.isReadable(dataLength)) {//数据不够长，重置位置
            msg.resetReaderIndex();
            return;
        }

        // 读取消息内容
        byte[] body = new byte[dataLength];
        msg.readBytes(body, 0, body.length);
        ReqProtoMsg reqProtoMsg = new ReqProtoMsg();
        reqProtoMsg.setMsgId(messageId);
        reqProtoMsg.setData(body);
        // 将消息内容解码成字符串，并添加到输出列表中
        out.add(reqProtoMsg);
    }
}
