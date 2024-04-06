package chen.handler;

import chen.config.ProtocolConstants;
import chen.msg.ProtoMsg;
import chen.utils.BitUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class MessageEncoder extends MessageToByteEncoder<ProtoMsg> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtoMsg msg, ByteBuf out) throws Exception {

        short messageId = (short) msg.getCode();
        short headFlag = msg.getHead();
        byte[] data = msg.getData();
        short length = (short) ((data == null ? 0 : (short) data.length) + ProtocolConstants.HEAD_LENGTH);

        out.writeShort(BitUtils.SwapInt16(length));
        out.writeShort(BitUtils.SwapInt16(messageId));
        out.writeShort(headFlag);
        out.writeBytes(data);
    }
}
