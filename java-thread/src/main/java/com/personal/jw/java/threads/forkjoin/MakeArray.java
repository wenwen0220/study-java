package com.personal.jw.java.threads.forkjoin;

import java.util.Random;

/**
 * Created by jww on 2019/01/26.
 * Describe 随机数组
 */
public class MakeArray {
    public static final int ARRAY_LENGTH = 2330;

    public static int[] makeArray() {
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = i;
        }
        return result;
    }
}
