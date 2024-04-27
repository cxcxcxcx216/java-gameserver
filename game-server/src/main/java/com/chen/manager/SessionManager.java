package com.chen.manager;

import com.chen.net.Session;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static SessionManager INSTANCE;

    private static final Map<ChannelHandlerContext, Session> sessionMap = new ConcurrentHashMap<>();

    public static SessionManager getInstance(){
        if (INSTANCE==null)
            return new SessionManager();
        else
            return INSTANCE;
    }

    public Session getSession(ChannelHandlerContext ctx){
        Session session = sessionMap.get(ctx);
        return session;
    }

    public Session removeSession(ChannelHandlerContext ctx){
        Session remove = sessionMap.remove(ctx);
        return remove;
    }

    public Session addSession(ChannelHandlerContext ctx,Session session){
        return sessionMap.put(ctx, session);
    }
}
