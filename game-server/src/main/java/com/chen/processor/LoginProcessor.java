package com.chen.processor;



import com.chen.utils.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

public class LoginProcessor implements Processer{
    private static ExecutorService loinThreadPool = ThreadPoolUtil.createThreadPool();


    @Override
    public void addTask(Runnable runnable) {
        loinThreadPool.execute(runnable);
    }
}
