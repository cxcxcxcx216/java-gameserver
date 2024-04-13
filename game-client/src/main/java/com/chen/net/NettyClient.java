package com.chen.net;

import com.chen.net.handler.MessageDecoder;
import com.chen.net.handler.MessageEncoder;
import com.chen.net.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private final String host;
    private final int port;


    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MessageDecoder())
                                    .addLast(new MessageEncoder())
                                    .addLast(new ClientHandler());
                        }
                    });

            Channel channel = bootstrap.connect(host, port).sync().channel();
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }


//    private static class ClientHandler extends SimpleChannelInboundHandler<String> {
//        @Override
//        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//            System.out.println("Server response: " + msg);
//        }
//
//        @Override
//        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            // 连接建立时发送消息
//            ctx.writeAndFlush("Hello Server!");
//
//        }
//    }
}