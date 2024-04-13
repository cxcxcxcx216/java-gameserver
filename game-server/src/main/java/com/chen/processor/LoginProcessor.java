package com.chen.processor;



import com.chen.annotation.Processor;
import com.chen.utils.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;


@Processor(processorId = 101)
public class LoginProcessor implements Processer{
    private static ExecutorService loinThreadPool = ThreadPoolUtil.createThreadPool();


    @Override
    public void addTask(Runnable runnable) {
        loinThreadPool.execute(runnable);
    }

    @Override
    public int processId() {
        return 101;
    }
}
