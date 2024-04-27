package com.chen.common.base;

import com.chen.net.ProtobufMessage;

public abstract class BaseAction<T extends BaseActor> {

    public abstract ProtobufMessage doAction(ProtobufMessage request, T actor) throws Throwable;
}