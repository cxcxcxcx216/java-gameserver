package com.chen.net.handler;

import com.chen.entity.Player;
import com.chen.mannger.PlayerManager;
import com.chen.mannger.SessionManager;
import com.chen.net.msg.ReqProtoMsg;
import com.chen.net.Session;
import com.chen.processor.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ServerHandler extends SimpleChannelInboundHandler<ReqProtoMsg> {


    private Router router;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReqProtoMsg reqProtoMsg) throws Exception {

        log.info("收到客户端消息：{}",reqProtoMsg);
        router.dispatcher(reqProtoMsg, SessionManager.getInstance().getSession(ctx));
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
