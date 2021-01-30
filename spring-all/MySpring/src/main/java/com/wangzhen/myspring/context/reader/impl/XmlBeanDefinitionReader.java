package com.wangzhen.myspring.context.reader.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinition;
import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.bean.beandefinition.impl.DefaultBeanDefinition;
import com.wangzhen.myspring.context.resource.Resource;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description:
 * Datetime:    2020/11/2   13:12
 * Author:   王震
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeandefinition(Resource resource) throws DocumentException, IOException, ClassNotFoundException {
        parse(resource);
    }

    @Override
    public void loadBeandefinition(Resource... resource) throws IOException, ParserConfigurationException, SAXException, DocumentException, ClassNotFoundException {
        for (Resource res : resource) {
            parse(res);
        }
    }
    private void parse(Resource res) throws IOException, DocumentException, ClassNotFoundException {
        InputStream inputStream = res.getInputStream();
        //获取document对象
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        //解析
        List<Element> elements = rootElement.elements();
        for(Element element:elements){
            List<Attribute> attributes = element.attributes();
            BeanDefinition beanDefinition = new DefaultBeanDefinition();
            for(Attribute attribute:attributes){
                //class标签
                if("class".equals(attribute.getName())){
                    String data = (String) attribute.getData();
                    Class<?> aClass = Class.forName(data);
                    ((DefaultBeanDefinition) beanDefinition).setClazz(aClass);
                }else if("id".equals(attribute.getName())){
                    //class标签
                    String data = (String) attribute.getData();
                    ((DefaultBeanDefinition) beanDefinition).setBeanName(data);
                }
                //todo 其他标签
            }

            if(beanDefinition.getBeanName() != null){
                this.registry.register(beanDefinition, beanDefinition.getBeanName());
            }
        }
    }
}