package com.personal.jw.java.threads;

import com.personal.jw.java.threads.util.SleepTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by jww on 2020/04/01.
 * Describe
 */
public class UseThreadPool {
    //工作线程
    static class Worker implements Runnable
    {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()
                    +" process the task : " + taskName);
            SleepTools.sleep(r.nextInt(100)*5);
        }
    }

    static class CallWorker implements Callable<String> {

        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
                    +" process the task : " + taskName);
            Thread.sleep(5000);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        List<Future<String>> list = Collections.synchronizedList(new ArrayList<>());
//    	ExecutorService pool = new ThreadPoolExecutor(2,4,3,TimeUnit.SECONDS,
//    			new ArrayBlockingQueue<Runnable>(10),
//    			new ThreadPoolExecutor.DiscardOldestPolicy());
        ExecutorService pool = Executors.newCachedThreadPool();//其实是比较少
//        for(int i=0;i<6;i++) {
//            Worker worker = new Worker("worker_"+i);
//            pool.execute(worker);
//        }
        for(int i=0;i<6;i++) {
            CallWorker callWorker = new CallWorker("callWorker_"+i);
            Future<String> result = pool.submit(callWorker);
            list.add(result);
//            System.out.println(result.get());
//            System.out.println("1");
        }

        for(Future<String> future:list){
            String a=future.get();
            System.out.println(a);
        }
        pool.shutdown();
    }
}
