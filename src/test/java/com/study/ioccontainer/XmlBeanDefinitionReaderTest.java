package com.study.ioccontainer;

import com.study.ioccontainer.definition.BeanDefinition;
import com.study.ioccontainer.definition.reader.xml.XmlBeanDefinitionReader;
import com.study.ioccontainer.exceptions.BeanInstantinationException;
import org.junit.Test;

import java.util.List;

public class XmlBeanDefinitionReaderTest {
    @Test(expected = BeanInstantinationException.class)
    public void testXmlBeanDefinitionReader() {
        new XmlBeanDefinitionReader("aaa");
    }

    @Test
    public void testReadBeanDefinitions() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader("src/test/resources/beans.xml");
        List<BeanDefinition> beanDefinitions = xmlBeanDefinitionReader.readBeanDefinitions();

        for (BeanDefinition beanDefinition : beanDefinitions) {
            System.out.println(beanDefinition.toString());
        }
    }
}
