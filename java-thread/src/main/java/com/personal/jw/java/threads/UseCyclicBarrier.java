package com.personal.jw.java.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by jww on 2019/01/28.
 * Describe
 * 1、countdownlatch放行由第三者控制，CyclicBarrier放行由一组线程本身控制
 * 2、countdownlatch放行条>=线程数，CyclicBarrier放行条件=线程数
 */
public class UseCyclicBarrier {
    //这个传入一个，其收集结果的线程
    private static CyclicBarrier barrier = new CyclicBarrier(4,new CollectThread());
    //存放子线程结果
    private static ConcurrentHashMap result=new ConcurrentHashMap();

    //工作线程
    private static class WorkThread implements Runnable {

        @Override
        public void run() {
            System.out.println("thread id:"+Thread.currentThread().getId());

            result.put(Thread.currentThread().getName(),Thread.currentThread().getId());
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    //统计线程,在其他线程await之后执行
    private static class CollectThread implements Runnable{
        @Override
        public void run(){
//            result.forEach((k,v)->{
//                System.out.println(k+","+v);
//            });
            System.out.println(result);
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<=3;i++){
            new Thread(new WorkThread()).start();
        }
    }

}
