package com.personal.jw.java.spring.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by jww on 2020/02/05.
 * Describe 可以自定义选择要注入的类
 */
public class AnimalSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //全类名
        return new String[]{"com.personal.jw.java.spring.cap1.entity.Fish"};
    }
}
