package com.chen.mannger;

import com.chen.entity.Player;
import io.netty.channel.ChannelHandlerContext;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private static PlayerManager INSTANCE;
    private static final Map<ChannelHandlerContext, Player> playerOnLineMap = new ConcurrentHashMap();
    private static final Map<ChannelHandlerContext, Player> playerOffLineMap = new ConcurrentHashMap();



    public static PlayerManager getInstance(){
        if (INSTANCE == null)
            INSTANCE = new PlayerManager();
        return INSTANCE;
    }
    public  boolean addPlayer(ChannelHandlerContext ctx, Player player) {
        if (playerOnLineMap.getOrDefault(ctx, null) == null) {
            return false;
        } else {
            Player onlinePlayer =  playerOnLineMap.put(ctx, player);
            return onlinePlayer.getSession().getCtx() == player.getSession().getCtx();
        }
    }

    public  boolean removePlayer(ChannelHandlerContext ctx) {
        playerOnLineMap.remove(ctx);
        return playerOnLineMap.getOrDefault(ctx, null) == null;
    }

    public  boolean addPlayerOffLineMap(ChannelHandlerContext ctx) {
        Player player =  playerOnLineMap.remove(ctx);
        playerOffLineMap.put(ctx, player);
        return playerOffLineMap.getOrDefault(ctx, null) != null;
    }

    public  boolean removePlayerOffLineMap(ChannelHandlerContext ctx) {
        Player player =  playerOffLineMap.remove(ctx);
        return player != null;
    }

    public  Player getPlayer(ChannelHandlerContext ctx) {
        Player player =  playerOnLineMap.getOrDefault(ctx, null);
        return player;
    }

}