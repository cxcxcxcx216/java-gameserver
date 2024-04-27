package com.chen.common.base;

import akka.actor.UntypedAbstractActor;

import java.util.Map;

public abstract class BaseActor extends UntypedAbstractActor {

    protected Map<Integer, BaseAction<? extends BaseActor>> actionMap;

    @Override
    public void onReceive(Object message) throws Throwable {

    }
}
