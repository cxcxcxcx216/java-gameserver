package com.chen.net;

import com.chen.entity.Player;
import com.chen.net.msg.ReqProtoMsg;
import com.chen.net.msg.ResProtoMsg;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Session {

    private ChannelHandlerContext ctx;

    private Player player;

    private Map<Object,Object> values = new HashMap<>();

    public void sendMsg(ResProtoMsg msg){
        ctx.writeAndFlush(msg);
    }
}
