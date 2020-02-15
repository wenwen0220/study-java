package com.personal.jw.java.spring.annotation.config;

import com.personal.jw.java.spring.annotation.entity.Pig;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by jww on 2020/02/05.
 * Describe 可以写判断条件、把所有需要的bean注入到容器,所有的bean都可以使用BeanDefinitionRegistry
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 如果容器中存在，dog、cat那么就把pig注入
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean bean1 = registry.containsBeanDefinition("com.personal.jw.java.spring.cap1.entity.Dog");
        boolean bean2 = registry.containsBeanDefinition("com.personal.jw.java.spring.cap1.entity.Cat");
        if(bean1 && bean2){
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Pig.class);
            registry.registerBeanDefinition("pig", beanDefinition);
        }
    }
}
