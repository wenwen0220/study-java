package com.personal.jw.java.threads.semaphore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by jww on 2019/01/28.
 * Describe 控制同时访问某个特定资源的线程数量，经常用在流量控制
 */
public class DbPoolSemaphore {
    private static final int POOL_SIZE = 10;
    //useful表示池中可用的数量，useless表示已经再用的连接数量
    private final Semaphore useful, useless;

    //初始化
    public DbPoolSemaphore() {
        this.useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    //用来存放连接的容器
    private static LinkedList<Connection> pool = new LinkedList<>();

    private static final String url = "";
    private static final String user = "";
    private static final String password = "";

    //初始化连接池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.addLast(conn);
        }
    }

    //从池子里拿链接
    public Connection takeConnection() throws InterruptedException {
        //如果没有的话，这里就一直阻塞
        useful.acquire();
        Connection conn;
        synchronized (pool) {
            conn = pool.removeFirst();
        }
        //useless+1
        useless.release();
        return conn;
    }

    //回收链接
    public void returnConnection(Connection conn) throws InterruptedException {

        if (conn != null) {
            //没有再用的链接话，这里阻塞
            useless.acquire();

            synchronized (pool) {
                pool.addLast(conn);
            }
            //useful-1
            useful.release();
        }

    }
}
