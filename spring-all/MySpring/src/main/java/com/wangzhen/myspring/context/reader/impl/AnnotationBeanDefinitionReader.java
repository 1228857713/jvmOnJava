package com.wangzhen.myspring.context.reader.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.context.resource.Resource;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Description:
 * Datetime:    2020/11/2   4:55 下午
 * Author:   王震
 */
public class AnnotationBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeandefinition(Resource resource) throws DocumentException, IOException, ClassNotFoundException {

    }

    @Override
    public void loadBeandefinition(Resource... resource) throws IOException, ParserConfigurationException, SAXException, DocumentException, ClassNotFoundException {

    }
}
