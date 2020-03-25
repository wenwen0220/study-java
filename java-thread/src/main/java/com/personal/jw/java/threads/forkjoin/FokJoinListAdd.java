package com.personal.jw.java.threads.forkjoin;

import com.personal.jw.java.threads.util.SleepTools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static com.personal.jw.java.threads.forkjoin.MakeArray.makeArray;

/**
 * Created by jww on 2020/03/24.
 * Describe 返回list
 */
public class FokJoinListAdd {
    private static class ListTask extends RecursiveTask<List> {
        private static final int SPLITS_ARRAY = MakeArray.ARRAY_LENGTH / 10;//每次处理的量
        private int[] x;//要统计的数组
        private int fromIndex;//开始统计的下标
        private int toIndex;//统计结束的下标

        //构造
        public ListTask(int[] x, int fromIndex, int toIndex) {
            this.x = x;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected List<String> compute() {
            if (toIndex - fromIndex < SPLITS_ARRAY) {
                List<String> list=new ArrayList<>();
                for (int i = fromIndex; i <= toIndex; i++) {
                    SleepTools.sleep(1);
                    makeArray();
                    list.add(String.valueOf(i));
                }
                return list;
            } else {
                int mid = (fromIndex + toIndex) / 2;
                ListTask leftTask = new ListTask(x, fromIndex, mid);
                ListTask rightTask = new ListTask(x, mid + 1, toIndex);
                invokeAll(leftTask, rightTask);
                List<String> left=leftTask.join();
                List<String> right=rightTask.join();
                left.addAll(right);
                return left;
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //使用流程
        //1.需要先实例化一个forkjoin池
        //2.需要有一个task实现RecursiveTask（有返回值的时候），实现RecursiveAction（无返回值的时候）
        //3.通过调用pool.invoke(innerTask);将任务提交给池
        //4.获得结果用任务的join方法innerTask.join()
        ForkJoinPool pool = new ForkJoinPool(10);
        int[] x = makeArray();
        System.out.println(x.length);
        ListTask innerTask = new ListTask(x, 0, x.length - 1);
        //同步调用，异步调用的话，是excute()方法
        pool.invoke(innerTask);
        System.out.println("result is:" + innerTask.join());
        System.out.println(innerTask.join().size());
        System.out.println("spend time:" + (System.currentTimeMillis() - start));
//        spend time:610
    }
}
