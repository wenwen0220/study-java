package com.personal.jw.java.spring.annotation.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by jww on 2019/02/05.
 * Describe
 */
public class Person {

    //value可直接对变量初始化，赋值
    @Value("jww")
    private String name;

    //可以直接读取配置文件的值
    @Value("${person.age}")
    private String age;
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
