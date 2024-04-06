package chen.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {
    public static ExecutorService createThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService;
    }
}
