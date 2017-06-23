package com.example.p2p.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenyuelun on 2017/6/21.
 */

public class ThreadManager {

    private static ThreadManager threadManager = new ThreadManager();

    private ThreadManager(){}

    public static ThreadManager getInstance(){
        return threadManager;
    }

    private ExecutorService service = Executors.newCachedThreadPool();

    public ExecutorService getThread(){
        return service;
    }

}
