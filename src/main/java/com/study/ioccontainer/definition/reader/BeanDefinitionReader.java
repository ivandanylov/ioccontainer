package com.study.ioccontainer.definition.reader;

import com.study.ioccontainer.definition.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {
    List<BeanDefinition> readBeanDefinitions();
}
