package com.personal.jw.java.threads.forkjoin;

import com.personal.jw.java.threads.util.SleepTools;

/**
 * Created by jww on 2019/01/26.
 * Describe 正常统计
 */
public class SumNormal {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] x = MakeArray.makeArray();
        long count = 0;
        for (int i : x) {
            SleepTools.sleep(1);
            count += i;
        }
        System.out.println("result is :" + count);
        System.out.println("spend time:" + (System.currentTimeMillis() - start));
//        spend time:4902
    }
}
