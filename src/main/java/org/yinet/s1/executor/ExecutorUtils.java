package org.yinet.s1.executor;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ppdashi on 2017/7/15.
 */
public class ExecutorUtils {
    /**定时器*/
    public static ScheduledExecutorService timerTask;
    /**单线程线程池*/
    public static ExecutorService threadTask;
    static {
        threadTask = Executors.newSingleThreadExecutor();
        timerTask = Executors.newScheduledThreadPool(1);
    }
//    private void init(){
//        timerTask.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println("delay 1 seconds, and excute every 3 seconds");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//    }

//    public static void main(String[] args) {
//        timerTask.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println("delay 1 seconds, and excute every 3 seconds");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//    }
}
