package com.personal.jw.java.spring.aop;

import com.personal.jw.java.spring.aop.app.Calculator;
import com.personal.jw.java.spring.aop.config.AopMainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by jww on 2020/02/08.
 * Describe
 */
public class aopTest {
    public static void main(String[] args) {
        ApplicationContext app=new AnnotationConfigApplicationContext(AopMainConfig.class);
        Calculator c=app.getBean(Calculator.class);
        int result=c.div(6,2);

    }
}
