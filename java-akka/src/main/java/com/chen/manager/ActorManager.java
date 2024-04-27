package com.chen.manager;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.HashMap;
import java.util.Map;

public class ActorManager {


    private static ActorManager INSTANCE;
    // 创建 ActorSystem
    private static final ActorSystem system  = ActorSystem.create("system");

    private static final Map<Integer,ActorRef> playerActorMap = new HashMap<>();

    public static ActorManager getInstance(){
        if (INSTANCE==null) {
            INSTANCE = new ActorManager();
        }
        return INSTANCE;
    }


    public ActorRef createActor(Class clz,String actorName,int id){
        ActorRef actorRef = system.actorOf(Props.create(clz), actorName);
        playerActorMap.put(id,actorRef);
        return actorRef;
    }


    public void removeActor(int id){
        playerActorMap.remove(id);
    }


    public ActorSystem getSystem(){
        return system;
    }




}
