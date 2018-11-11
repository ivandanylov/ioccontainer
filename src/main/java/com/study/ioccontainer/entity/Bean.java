package com.study.ioccontainer.entity;

import com.study.ioccontainer.exceptions.BeanInstantinationException;

public class Bean {
    private String id;
    private Object value;

    public Bean() {}

    public Bean(String id, Object value) {
        setId(id);
        setValue(value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new BeanInstantinationException("Bean id is empty");
        }
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value == null) {
            throw new BeanInstantinationException("Bean value is empty");
        }
        this.value = value;
    }
}
