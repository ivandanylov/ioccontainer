package com.study.ioccontainer;

import com.study.ioccontainer.entity.Bean;
import com.study.ioccontainer.exceptions.BeanInstantinationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanTest {
    private Bean bean;

    @Before
    public void before() {
        bean = new Bean();
    }

    @Test
    public void getId() {
        bean.setId("bean");
        assertEquals("bean", bean.getId());
    }

    @Test(expected = BeanInstantinationException.class)
    public void setIdNull() {
        bean.setId(null);
    }

    @Test(expected = BeanInstantinationException.class)
    public void setIdEmpty() {
        bean.setId("");
    }

    @Test
    public void getValue() {
        bean.setValue(1);
        assertEquals(1, bean.getValue());
    }

    @Test(expected = BeanInstantinationException.class)
    public void setValueNull() {
        bean.setValue(null);
    }
}