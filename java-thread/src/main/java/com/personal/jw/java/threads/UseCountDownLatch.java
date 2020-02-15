package com.personal.jw.java.threads;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jww on 2019/01/27.
 * Describe 加强版的join
 * 比如一个表单中统计多个数据，可以启动4个线程，都处理完之后，主线程再继续向下执行
 */
public class UseCountDownLatch {
    //初始化数量需要>=线程数
    static CountDownLatch latch = new CountDownLatch(6);

    //初始化线程
    private static class InitThread implements Runnable {

        @Override
        public void run() {
            System.out.println("init thread");
            latch.countDown();
            System.out.println("continu do ");
        }
    }

    //业务线程
    static class BusiThread implements Runnable {

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do business");
        }
    }

    public static void main(String[] args) {
        //这下边同时启动多个线程，具体哪个线程先执行，是由操作系统控制的，我们控制不了
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("main thread step1.");
                latch.countDown();
                System.out.println("main thread step2.");
                latch.countDown();
            }
        }).start();
        new Thread(new BusiThread()).start();
        for (int i = 0; i <= 3; i++) {
            new Thread(new InitThread()).start();
        }
        try {
            //初始化线程决定是否向下执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");

    }
}
