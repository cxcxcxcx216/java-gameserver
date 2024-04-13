package com.chen.net.handler;

import com.chen.entity.Player;
import com.chen.mannger.PlayerManager;
import com.chen.mannger.SessionManager;
import com.chen.msg.ProtoMsg;
import com.chen.net.Session;
import com.chen.processor.Router;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ServerHandler extends SimpleChannelInboundHandler<ProtoMsg> {


    private Router router;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMsg protoMsg) throws Exception {
        router.dispatcher(protoMsg, SessionManager.getInstance().getSession(ctx));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Player player = new Player();
        Session session = new Session();
        session.setCtx(ctx);
        player.setSession(session);
        session.setPlayer(player);
        PlayerManager.getInstance().addPlayer(ctx,player);
        SessionManager.getInstance().addSession(ctx,session);
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
