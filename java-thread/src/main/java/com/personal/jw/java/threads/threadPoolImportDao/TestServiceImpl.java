package com.personal.jw.java.threads.threadPoolImportDao;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class TestServiceImpl {

    public List<String> getUserById() {

        //初始化线程池
        ExecutorService threadPool = ThreadPoolWrap.getInstance().getThreadPool();
        //线程安全的list,用来装线程执行之后的future
        List<Future<String>> list = Collections.synchronizedList(new ArrayList<>());

        //存放最终结果
        List<String> listResult = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //初始化CallTask对象后，用submit执行，无返回值的时候用excute执行
            Future<String> submit = threadPool.submit(new CallTask());
            //将线程执行的结果，放到线程安全的容器里
            list.add(submit);
        }

        for (Future<String> future : list) {
            try {
                //拿到结果，get是阻塞的方法
                String result = future.get();
                //结果放到最终的容器
                listResult.add(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return listResult;
    }

}