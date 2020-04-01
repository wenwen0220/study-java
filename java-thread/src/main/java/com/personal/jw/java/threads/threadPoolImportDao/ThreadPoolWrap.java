package com.personal.jw.java.threads.threadPoolImportDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 初始化线程池
 */
public class ThreadPoolWrap
{

    private volatile static ThreadPoolWrap instance = null;
    private static ExecutorService fixedThreadPool = null;

    private ThreadPoolWrap()
    {
        fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public static ThreadPoolWrap getInstance()
    {

        if (instance == null)
        {
            synchronized (ThreadPoolWrap.class)
            {
                if (instance == null)
                {
                    instance = new ThreadPoolWrap();
                }
            }
        }
        return instance;
    }

    public ExecutorService getThreadPool()
    {
        return fixedThreadPool;
    }
}
