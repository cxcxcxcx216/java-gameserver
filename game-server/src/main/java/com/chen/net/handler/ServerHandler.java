package com.chen.net.handler;

import com.chen.entity.Player;
import com.chen.mannger.PlayerManager;
import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
import com.chen.processor.Router;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;


@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String protoMsg) throws Exception {
        log.info(protoMsg);
//        Router.dispatcher(protoMsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Player player = new Player();
        Session session = new Session();
        session.setCtx(ctx);
        player.setSession(session);
        session.setPlayer(player);
        PlayerManager.getInstance().addPlayer(ctx,player);
        ctx.writeAndFlush("服务器链接成功!");
        log.info("用户数据创建成功->{}==IP:{}",player,ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        PlayerManager.getInstance().addPlayerOffLineMap(ctx);
        log.info("{}->断开链接",ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("{}->断开链接",ctx.channel().remoteAddress());
        PlayerManager.getInstance().removePlayer(ctx);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            log.info("{}->断开链接",ctx.channel().remoteAddress().toString());
            PlayerManager.getInstance().removePlayer(ctx);
            ctx.close();
        }
    }
}
