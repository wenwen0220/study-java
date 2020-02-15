package com.personal.jw.java.spring.annotation.config;

import com.personal.jw.java.spring.annotation.entity.MonKey;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by jww on 2020/02/05.
 * Describe
 */
public class CustomFactoryBean implements FactoryBean<MonKey> {
    @Override
    public MonKey getObject() throws Exception {
        return new MonKey();
    }

    @Override
    public Class<?> getObjectType() {
        return MonKey.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
