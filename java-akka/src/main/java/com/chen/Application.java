package com.chen;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.chen.actor.PlayerActor;
import com.chen.manager.ActorManager;

public class Application {


    public static void main(String[] args) {
        ActorRef actor = ActorManager.getInstance().createActor(PlayerActor.class, "chenxing", 10);

        long num = 999999999999999999l;
        while (num>0){
            actor.tell("hello world",actor);
            num--;
        }


        ActorSystem system = ActorManager.getInstance().getSystem();
        system.terminate();


    }
}
