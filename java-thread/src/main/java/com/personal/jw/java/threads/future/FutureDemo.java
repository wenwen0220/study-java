package com.personal.jw.java.threads.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by jww on 2019/01/28.
 * Describe callable可以抛出异常，也可以有返回值
 * 比如：包含图片和文字的文档的处理：图片（云上），可以用future去取图片，主线程继续解析文字。
 */
public class FutureDemo {
    private static class UseCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            return 1000;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();
        //需要用futreTask接收
        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);
        //放在另外的一个线程里启动。
        new Thread(futureTask).start();
        //get获取返回值，是一个阻塞方法
        System.out.println(futureTask.get());
        System.out.println("end");
    }
}
