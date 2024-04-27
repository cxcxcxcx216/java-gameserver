package com.chen.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import com.chen.common.base.BaseActor;

public class PlayerActor extends BaseActor {


    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println((String) message);
        ActorRef sender = getSender();
        sender.tell("收到消息",ActorRef.noSender());
    }
}
