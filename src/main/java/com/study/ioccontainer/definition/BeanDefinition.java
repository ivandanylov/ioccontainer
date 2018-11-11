package com.study.ioccontainer.definition;

import com.study.ioccontainer.exceptions.BeanInstantinationException;

import java.util.Map;
import java.util.StringJoiner;

public class BeanDefinition {
    private String id;
    private String className;
    private Map<String, String> valuesDependencies;
    private Map<String, String> refDependencies;

    public BeanDefinition(String id, String className) {
        setId(id);
        setClassName(className);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new BeanInstantinationException("BeanDefinition id is empty");
        }

        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        if (className == null || className.isEmpty()) {
            throw new BeanInstantinationException("BeanDefinition class name is empty");
        }

        this.className = className;
    }

    public Map<String, String> getValuesDependencies() {
        return valuesDependencies;
    }

    public void setValuesDependencies(Map<String, String> valuesDependencies) {
        if (valuesDependencies == null) {
            throw new BeanInstantinationException("BeanDefinition values dependencies is empty");
        }

        this.valuesDependencies = valuesDependencies;
    }

    public Map<String, String> getRefDependencies() {
        return refDependencies;
    }

    public void setRefDependencies(Map<String, String> refDependencies) {
        if (refDependencies == null) {
            throw new BeanInstantinationException("BeanDefinition reference dependencies is empty");
        }

        this.refDependencies = refDependencies;
    }

    @Override
    public String toString() {
        StringJoiner values = new StringJoiner("");
        StringJoiner refs = new StringJoiner("");

        for (Map.Entry entry : valuesDependencies.entrySet()) {
            values.add(String.format("\n\t<property name=\"%s\" value=\"%s\"/>", entry.getKey(), entry.getValue()));
        }

        for (Map.Entry entry : refDependencies.entrySet()) {
            refs.add(String.format("\n\t<property name=\"%s\" ref=\"%s\"/>", entry.getKey(), entry.getValue()));
        }

        return String.format("<bean id=\"%s\" class=\"%s\">%s%s\n</bean>", id, className, values.toString(), refs.toString());
    }
}
