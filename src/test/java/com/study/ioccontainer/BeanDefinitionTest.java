package com.study.ioccontainer;

import com.study.ioccontainer.definition.BeanDefinition;
import com.study.ioccontainer.exceptions.BeanInstantinationException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BeanDefinitionTest {
    private BeanDefinition beanDefinition;

    @Before
    public void before() {
        beanDefinition = new BeanDefinition("bean", "bean.class");
    }

    @Test
    public void getId() {
        assertEquals("bean", beanDefinition.getId());
    }

    @Test(expected = BeanInstantinationException.class)
    public void setIdNull() {
        beanDefinition.setId(null);
    }

    @Test(expected = BeanInstantinationException.class)
    public void setIdEmpty() {
        beanDefinition.setId("");
    }

    @Test
    public void getClassName() {
        assertEquals("bean.class", beanDefinition.getClassName());
    }

    @Test(expected = BeanInstantinationException.class)
    public void setClassNameNull() {
        beanDefinition.setClassName(null);
    }

    @Test(expected = BeanInstantinationException.class)
    public void setClassNameEmpty() {
        beanDefinition.setClassName("");
    }

    @Test
    public void getValuesDependencies() {
        Map<String, String> valuesDependencies = new HashMap<>();
        valuesDependencies.put("property1", "value1");
        valuesDependencies.put("property2", "value2");
        valuesDependencies.put("property3", "value3");
        beanDefinition.setValuesDependencies(valuesDependencies);

        assertEquals(valuesDependencies.size(), beanDefinition.getValuesDependencies().size());
        assertThat(valuesDependencies, is(beanDefinition.getValuesDependencies()));
        assertThat(beanDefinition.getValuesDependencies().size(), is(3));
        assertEquals("value1", beanDefinition.getValuesDependencies().get("property1"));
        assertEquals("value2", beanDefinition.getValuesDependencies().get("property2"));
        assertEquals("value3", beanDefinition.getValuesDependencies().get("property3"));
    }

    @Test(expected = BeanInstantinationException.class)
    public void setValuesDependenciesNull() {
        beanDefinition.setValuesDependencies(null);
    }

    @Test
    public void getRefDependencies() {
        Map<String, String> refDependencies = new HashMap<>();
        refDependencies.put("property1", "bean1");
        refDependencies.put("property2", "bean2");
        refDependencies.put("property3", "bean3");
        beanDefinition.setRefDependencies(refDependencies);

        assertEquals(refDependencies.size(), beanDefinition.getRefDependencies().size());
        assertThat(refDependencies, is(beanDefinition.getRefDependencies()));
        assertThat(beanDefinition.getRefDependencies().size(), is(3));
        assertEquals("bean1", beanDefinition.getRefDependencies().get("property1"));
        assertEquals("bean2", beanDefinition.getRefDependencies().get("property2"));
        assertEquals("bean3", beanDefinition.getRefDependencies().get("property3"));
    }

    @Test(expected = BeanInstantinationException.class)
    public void setRefDependenciesNull() {
        beanDefinition.setRefDependencies(null);
    }

    @Test
    public void getBeanDefinitionString() {
        Map<String, String> valuesDependencies = new HashMap<>();
        valuesDependencies.put("property1", "value1");
        valuesDependencies.put("property2", "value2");
        valuesDependencies.put("property3", "value3");
        beanDefinition.setValuesDependencies(valuesDependencies);

        Map<String, String> refDependencies = new HashMap<>();
        refDependencies.put("property4", "bean1");
        refDependencies.put("property5", "bean2");
        refDependencies.put("property6", "bean3");
        beanDefinition.setRefDependencies(refDependencies);

        System.out.println(beanDefinition.toString());
    }
}