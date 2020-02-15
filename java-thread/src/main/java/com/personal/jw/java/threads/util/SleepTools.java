package com.personal.jw.java.threads.util;

/**
 * Created by jww on 2019/01/26.
 * Describe
 */
public class SleepTools {
    public static void sleep(long x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
