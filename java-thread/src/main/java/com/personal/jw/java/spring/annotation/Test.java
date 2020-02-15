package com.personal.jw.java.spring.annotation;

import com.personal.jw.java.spring.annotation.config.MainConfig;
import com.personal.jw.java.spring.annotation.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by jww on 2019/02/05.
 * Describe
 */
public class Test {

    public static void main(String[] args) {
        //初始化容器类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        //所有的实例的名
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(s -> System.out.println(s));
        //特定类型的实例名
        String[] names=applicationContext.getBeanNamesForType(Person.class);
        for(String name:names){
            System.out.println(name);
        }

        //FactoryBeanfff
        Object obj1=applicationContext.getBean("customFactoryBean");
        System.out.println(obj1.getClass());

        //这两种方式都可以从容器中，拿到同一个bean
        System.out.println(applicationContext.getBean(Person.class));
        System.out.println(applicationContext.getBean("person"));


    }

}
