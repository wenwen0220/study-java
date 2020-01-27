package com.personal.jw.java.threads.forkjoin;

import com.personal.jw.java.threads.util.SleepTools;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by jww on 2019/01/26.
 * Describe fork join
 * 什么是分而治之？
 * 规模为N的问题，N<阈值，直接解决，N>阈值，将N分解为K个小规模子问题，子问题互相对立，与原问题形式相同，将子问题的解合并得到原问题的解
 * 动态规范
 * 工作密取(一个线程执行完了，可以去执行另一个线程下的任务，减少整个任务的运行时间)
 * workStealing
 * 线程的上下文切换也会消耗一些时间，所以要注意多线程的使用时机
 */
public class ForkJoinSum {
    private static class SumTask extends RecursiveTask<Integer>{
        private static final int SPLITS_ARRAY= MakeArray.ARRAY_LENGTH/10;//每次处理的量
        private int[] x;//要统计的数组
        private int fromIndex;//开始统计的下标
        private int toIndex;//统计结束的下标

        //构造
        public SumTask(int[] x,int fromIndex,int toIndex){
            this.x=x;
            this.fromIndex=fromIndex;
            this.toIndex=toIndex;
        }

        @Override
        protected Integer compute() {
            if(toIndex-fromIndex<SPLITS_ARRAY){
                int count=0;
                for(int i=fromIndex;i<=toIndex;i++){
                    SleepTools.sleep(1);
                    count+=x[i];
                }
                return count;
            }else {
                int mid=(fromIndex+toIndex)/2;
                SumTask leftTask=new SumTask(x,fromIndex,mid);
                SumTask rightTask=new SumTask(x,mid+1,toIndex);
                invokeAll(leftTask,rightTask);
                return leftTask.join()+rightTask.join();
            }
        }
    }

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        //使用流程
        //1.需要先实例化一个forkjoin池
        //2.需要有一个task实现RecursiveTask（有返回值的时候），实现RecursiveAction（无返回值的时候）
        //3.通过调用pool.invoke(innerTask);将任务提交给池
        //4.获得结果用任务的join方法innerTask.join()
        ForkJoinPool pool=new ForkJoinPool(10);
        int[] x=MakeArray.makeArray();
        SumTask innerTask=new SumTask(x,0,x.length-1);
        pool.invoke(innerTask);
        System.out.println("result is:"+innerTask.join());
        System.out.println("spend time:"+(System.currentTimeMillis()-start));
//        spend time:610
    }
}
