package com.wangzhen.myspring.context.reader;

import com.wangzhen.myspring.context.resource.Resource;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Description:
 * Datetime:    2020/11/2   13:08
 * Author:   王震
 */
public interface BeanDefinitionReader {

    void loadBeandefinition(Resource resource) throws DocumentException, IOException, ClassNotFoundException;
    void loadBeandefinition(Resource... resource) throws IOException, ParserConfigurationException, SAXException, DocumentException, ClassNotFoundException;
}