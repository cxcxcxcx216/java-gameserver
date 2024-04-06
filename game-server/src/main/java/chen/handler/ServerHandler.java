package chen.handler;

import chen.entity.Player;
import chen.mannger.PlayerMannger;
import chen.msg.ProtoMsg;
import chen.net.Session;
import chen.processor.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<ProtoMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtoMsg protoMsg) throws Exception {
        Router.dispatcher(protoMsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Player player = new Player();
        Session session = new Session();
        session.setCtx(ctx);
        player.setSession(session);
        PlayerMannger.addPlayer(ctx,player);
        log.info("用户数据创建成功=={}",player);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        PlayerMannger.addPlayerOffLineMap(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.close();
        }
    }
}
