package com.chen;

import com.chen.msg.ProtoMsg;
import com.chen.net.NettyClient;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;


@Slf4j
public class ApplicationClient {

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

        Scanner scanner = new Scanner(System.in);
        while (true){
            ProtoMsg protoMsg = new ProtoMsg();
            protoMsg.setMsgId((short) 1002);

            String message = scanner.nextLine();

            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);;
            protoMsg.setData(bytes);
            ctx.writeAndFlush(protoMsg);
            log.info("客户端向服务器发送消息->{}",bytes.length);
        }

    }
}
