package com.saicmotor.libcommon.util;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     @author : kuaipu
 *     e-mail : kuaipu@saicmotor.com
 *     time   : 15/11/2018
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class ThreadUtils {
    private static ThreadUtils mThreadUtils = new ThreadUtils();
    private int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private int KEEP_ALIVE_TIME = 1;
    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();
    private ExecutorService executorService;


    public static ThreadUtils getInstance() {
        return mThreadUtils;
    }

    private void ThreadUtils() {
        executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue);
    }


    /**
     * 新增任务至任务池
     *
     * @param runnable
     */
    private void addTask(Runnable runnable) {
        if (executorService != null) {
            executorService.execute(runnable);
        }
    }


}
