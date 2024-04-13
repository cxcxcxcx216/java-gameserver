package com.chen.processor;

public interface Processor {

    public void addTask(Runnable runnable);

    public int processId();
}
