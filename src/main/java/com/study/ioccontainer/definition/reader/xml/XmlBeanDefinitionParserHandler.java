package com.study.ioccontainer.definition.reader.xml;

import com.study.ioccontainer.definition.BeanDefinition;
import com.study.ioccontainer.exceptions.BeanInstantinationException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlBeanDefinitionParserHandler extends DefaultHandler {
    private List<BeanDefinition> beanDefinitions = null;
    private BeanDefinition beanDefinition = null;
    private Map<String, String> valuesDependencies = null;
    private Map<String, String> refDependencies = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("bean")) {
            String idValue = attributes.getValue("id").trim();

            if (beanDefinitions == null) {
                beanDefinitions = new ArrayList<>();
            } else {
                for (BeanDefinition beanDef : beanDefinitions) { // search duplicated bean id
                    if (beanDef.getId().equals(idValue)) {
                        throw new BeanInstantinationException(String.format("Duplicated bean id = %s", idValue));
                    }
                }
            }

            beanDefinition = new BeanDefinition(idValue, attributes.getValue("class").trim());
        } else if (qName.equalsIgnoreCase("property")) {
            if (valuesDependencies == null) {
                valuesDependencies = new HashMap<>();
            }

            if (refDependencies == null) {
                refDependencies = new HashMap<>();
            }

            String nameValue =  attributes.getValue("name");
            String valueValue = attributes.getValue("value");
            String refValue = attributes.getValue("ref");

            if (nameValue == null || nameValue.isEmpty()) {
                throw new BeanInstantinationException(String.format("Property name for bean id = \"%s\" is empty", beanDefinition.getId()));
            }

            if ((valueValue == null || valueValue.isEmpty()) & (refValue == null || refValue.isEmpty())) {
                throw new BeanInstantinationException(String.format("Property value or ref for bean id = \"%s\" and name = \"%s\" is empty", beanDefinition.getId(), nameValue));
            }

            try {
                if (refValue == null || refValue.isEmpty()) {
                    valuesDependencies.put(nameValue, valueValue);
                } else {
                    refDependencies.put(nameValue, refValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeanInstantinationException(String.format("Can't add property with name = %s for bean id = %s", nameValue, beanDefinition.getId()));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("bean")) {
            beanDefinition.setValuesDependencies(valuesDependencies);
            beanDefinition.setRefDependencies(refDependencies);
            beanDefinitions.add(beanDefinition);
            valuesDependencies = null;
            refDependencies = null;
        }
    }

    public List<BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }
}
