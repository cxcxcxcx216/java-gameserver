package com.chen;

import com.chen.msg.ProtoMsg;
import com.chen.net.NettyClient;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Application {

    public static ChannelHandlerContext ctx;

    public static void main(String[] args) throws InterruptedException {


        String host = "localhost";
        int port = 9001;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient(host, port).run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Thread.sleep(1000*3);



    }
}
