package com.chen.action;

import com.chen.actor.PlayerActor;
import com.chen.common.base.BaseAction;
import com.chen.net.ProtobufMessage;

public class PlayerLoginAction extends BaseAction<PlayerActor> {

    @Override
    public ProtobufMessage doAction(ProtobufMessage request, PlayerActor actor) throws Throwable {
        return null;
    }
}
