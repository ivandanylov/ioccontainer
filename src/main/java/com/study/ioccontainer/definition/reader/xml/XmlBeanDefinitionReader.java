package com.study.ioccontainer.definition.reader.xml;

import com.study.ioccontainer.definition.BeanDefinition;
import com.study.ioccontainer.definition.reader.BeanDefinitionReader;
import com.study.ioccontainer.exceptions.BeanInstantinationException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    private String path;

    public XmlBeanDefinitionReader(String path) {
        File filePath = new File(path);

        if (!(filePath.exists() || filePath.isFile())) {
            throw new BeanInstantinationException("Wrong file path");
        }

        this.path = path;
    }

    @Override
    public List<BeanDefinition> readBeanDefinitions() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        List<BeanDefinition> beanDefinitions;

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlBeanDefinitionParserHandler handler = new XmlBeanDefinitionParserHandler();
            saxParser.parse(new File(path), handler);
            beanDefinitions = handler.getBeanDefinitions();

            return beanDefinitions;
        } catch (ParserConfigurationException | org.xml.sax.SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
