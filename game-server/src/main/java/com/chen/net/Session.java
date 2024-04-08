package com.chen.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Session {

    private ChannelHandlerContext ctx;

    private Map<Object,Object> values = new HashMap<>();
}
