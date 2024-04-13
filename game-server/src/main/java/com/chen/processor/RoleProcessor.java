package com.chen.processor;


import com.chen.utils.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;

@com.chen.annotation.Processor(processorId = 102)
public class RoleProcessor implements Processor{

    private static ExecutorService loinThreadPool = ThreadPoolUtil.createThreadPool();

    @Override
    public void addTask(Runnable runnable) {
        loinThreadPool.execute(runnable);
    }

    @Override
    public int processId() {
        return 102;
    }
}
