package com.personal.jw.java.threads.threadPoolImportDao;

import java.util.concurrent.Callable;

/**
 * Created by jww on 2020/04/01.
 * Describe 带返回值的线程对象
 * spring是单实例的，不能用@Autowire注入容器的实例，需要自己去容器里拿
 */
public class CallTask implements Callable<String> {

    private TestDao testDao;

    public CallTask() {
        //*********这里是在容器里拿
        this.testDao= ApplicationContextUtil.getBean(TestDao.class);
    }

    @Override
    public String call() throws Exception {

        //这里调用testDao的方法
        String user = testDao.getUserById();

        return user;
    }
}
