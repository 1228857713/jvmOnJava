package com.wangzhen.myspring.context.app.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.bean.postprocessor.AopPostProcessor;

import com.wangzhen.myspring.context.reader.impl.XmlBeanDefinitionReader;
import com.wangzhen.myspring.context.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Datetime:    2020/11/2   11:06
 * Author:   王震
 */
public class XmlApplicationContext extends AbstractApplicationContext {

    private List<Resource> resources;


    /**
     * @desc 1.通过传入的地址 解析为 Resource 文件
     *       2.在通过resource 获取xml 配置文件
     *       3.通过xml 文件解析拿到实体类 注入到 beanFactory 中去
     * @param loc
     * @throws Exception
     */
    public XmlApplicationContext(String loc) throws Exception {
        resources = new ArrayList<>();
        resources.add(getResource(loc));
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) this.beanFactory);

        Resource []resourceArray = new Resource[resources.size()];
        for (int i = 0; i < resourceArray.length; i++) {
            resourceArray[i] = resources.get(i);
        }
        xmlBeanDefinitionReader.loadBeandefinition(resourceArray);
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanPostProcessor(AopPostProcessor processor) {

    }

    @Override
    public String[] getBeanNameForType(Class<?> tClass) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansForType(Class<?> clazz) {
        return null;
    }

    @Override
    public Class getType(String beanName) {
        return null;
    }
}