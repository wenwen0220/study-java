package com.personal.jw.java.cas;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jww on 2019/01/28.
 * Describe
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
//        new AtomicInteger();

        ConcurrentHashMap<String,Object> a=new ConcurrentHashMap();
        a.put("a",1);
        a.put("a",1);
        System.out.println(a.get("a"));
        a.put("a",2);
        System.out.println(a.get("a"));
    }
}
