package com.chen;

import com.chen.config.MsgCode;
import com.chen.net.NettyClient;
import com.chen.proto.ProtoMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


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
        while (true){

            Thread.sleep(1000);
            com.chen.proto.ProtoMsg.Person.Builder person = com.chen.proto.ProtoMsg.Person.newBuilder();
            com.chen.proto.ProtoMsg.Person person1 = person.setId(101).addEmail("123123").setName("chenxing").build();
            com.chen.msg.ProtoMsg protoMsg = new com.chen.msg.ProtoMsg();


            protoMsg.setMsgId(MsgCode.CreateRole);
            protoMsg.setData(person1.toByteArray());

            ctx.writeAndFlush(protoMsg);
        }



    }

}
