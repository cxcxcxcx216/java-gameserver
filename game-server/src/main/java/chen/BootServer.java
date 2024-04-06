package chen;

import chen.handler.MessageDecoder;
import chen.handler.MessageEncoder;
import chen.handler.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BootServer {
    private static final Logger logger = LoggerFactory.getLogger(BootServer.class);
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelInitializer<SocketChannel> channelChannelInitializer;

    public BootServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup, ServerBootstrap serverBootstrap, ChannelInitializer<SocketChannel> channelChannelInitializer) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.serverBootstrap = serverBootstrap;
        this.channelChannelInitializer = channelChannelInitializer;
    }

    public BootServer() {
    }


    public void createServer(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        logger.info("服务器启动中...");

        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup);

            serverBootstrap.channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MessageEncoder())
                                    .addLast(new MessageDecoder())
                                    .addLast(new ServerHandler());
                        }
                    });


            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("服务器启动成功！");
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
