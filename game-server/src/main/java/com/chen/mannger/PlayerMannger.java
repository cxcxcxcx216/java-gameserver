package com.chen.mannger;

import com.chen.entity.Player;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerMannger {
    private static final Map<ChannelHandlerContext, Player> playerOnLineMap = new ConcurrentHashMap();
    private static final Map<ChannelHandlerContext, Player> playerOffLineMap = new ConcurrentHashMap();

    public PlayerMannger() {
    }

    public static boolean addPlayer(ChannelHandlerContext ctx, Player player) {
        if (playerOnLineMap.getOrDefault(ctx, null) == null) {
            return false;
        } else {
            Player onlinePlayer =  playerOnLineMap.put(ctx, player);
            return onlinePlayer.getSession().getCtx() == player.getSession().getCtx();
        }
    }

    public static boolean removePlayer(ChannelHandlerContext ctx) {
        playerOnLineMap.remove(ctx);
        return playerOnLineMap.getOrDefault(ctx, null) == null;
    }

    public static boolean addPlayerOffLineMap(ChannelHandlerContext ctx) {
        Player player =  playerOnLineMap.remove(ctx);
        playerOffLineMap.put(ctx, player);
        return playerOffLineMap.getOrDefault(ctx, null) != null;
    }

    public static boolean removePlayerOffLineMap(ChannelHandlerContext ctx) {
        Player player =  playerOffLineMap.remove(ctx);
        return player != null;
    }

    public static Player getPlayer(ChannelHandlerContext ctx) {
        Player player =  playerOnLineMap.getOrDefault(ctx, null);
        return player;
    }

}