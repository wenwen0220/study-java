package com.personal.jw.java.spring.aop.config;

import com.personal.jw.java.spring.aop.app.Calculator;
import com.personal.jw.java.spring.aop.app.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by jww on 2020/02/08.
 * Describe 注入
 */
@EnableAspectJAutoProxy//这个注解使切面生效
@Configuration
public class AopMainConfig {
    @Bean
    public Calculator calculator(){
        return new Calculator();
    }
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }

}
