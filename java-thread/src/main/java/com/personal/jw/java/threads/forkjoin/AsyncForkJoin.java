package com.personal.jw.java.threads.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by jww on 20219/01/27.
 * Describe 遍历某目录下的所有txt文件
 * forkjoin无返回值，异步调用
 */
public class AsyncForkJoin {
    private static class FindDirsFiles extends RecursiveAction {
        private File path;

        public FindDirsFiles(File path) {
            this.path = path;
        }

        @Override
        protected void compute() {

            List<FindDirsFiles> tasks = new ArrayList<>();
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        tasks.add(new FindDirsFiles(file));
                    } else {

                        if (file.getAbsolutePath().endsWith(".txt"))
                            System.out.println("file is :" + file.getAbsolutePath());
                    }
                }
                if (!tasks.isEmpty()) {
                    for (FindDirsFiles task : invokeAll(tasks)) {
                        //等待子任务完成
                        task.join();
                    }
                }
            }

        }

    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        final String path = "/Users/didi/jw";
        FindDirsFiles task = new FindDirsFiles(new File(path));
        //异步调用
        forkJoinPool.execute(task);
        System.out.println("task started");
        //阻塞的方法，等待所有线程执行完成
        task.join();
        System.out.println("task ended");
    }
}
