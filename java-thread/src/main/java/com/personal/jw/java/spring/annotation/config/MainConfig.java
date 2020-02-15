package com.personal.jw.java.spring.annotation.config;

import com.personal.jw.java.spring.annotation.entity.Cat;
import com.personal.jw.java.spring.annotation.entity.Dog;
import com.personal.jw.java.spring.annotation.entity.Person;
import org.springframework.context.annotation.*;

/**
 * Created by jww on 2019/02/05.
 * Describe 自定义一个容器类,这里有4中注入的方式
 */
@Configuration
//2.可以直接用import导入
@Import(value = {Dog.class, Cat.class, AnimalSelector.class, CustomImportBeanDefinitionRegistrar.class})
@PropertySource(value="classpath:application.properties")//加载配置文件
//组件扫描的方式，注入包下边的类
@ComponentScan({"com.personal.jw.java.spring.annotation.service", "com.personal.jw.java.spring.annotation.controller"})
public class MainConfig {
    //1.可以用bean导入
    @Bean("person")
    public Person person() {
        System.out.println("person实例化");
        return new Person();
    }
    @Bean
    public CustomFactoryBean customFactoryBean(){
        return new CustomFactoryBean();
    }
}
