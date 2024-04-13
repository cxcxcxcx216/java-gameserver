package com.chen;

import com.chen.msg.ProtoMsg;
import com.chen.net.NettyClient;
import io.netty.channel.ChannelHandlerContext;

public class Application {

    public static ChannelHandlerContext ctx;

    public static void main(String[] args) throws InterruptedException {


        String host = "localhost";
        int port = 9001;
        new NettyClient(host, port).run();



    }
}
