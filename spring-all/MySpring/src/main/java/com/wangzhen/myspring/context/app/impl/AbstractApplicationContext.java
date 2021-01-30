package com.wangzhen.myspring.context.app.impl;

import com.wangzhen.myspring.bean.factory.BeanFactory;
import com.wangzhen.myspring.bean.factory.impl.DefaultBeanFactory;
import com.wangzhen.myspring.bean.postprocessor.AopPostProcessor;
import com.wangzhen.myspring.context.app.ApplicationContext;
import com.wangzhen.myspring.context.resource.Resource;
import com.wangzhen.myspring.context.resource.ResourceFactory;
import com.wangzhen.myspring.context.resource.impl.ClasspathResource;
import com.wangzhen.myspring.context.resource.impl.FileSystemResource;
import com.wangzhen.myspring.context.resource.impl.URLResource;

/**
 * Description:
 * Datetime:    2020/11/2   11:07
 * Author:   王震
 */
public abstract class AbstractApplicationContext  implements ApplicationContext , ResourceFactory {

    protected BeanFactory beanFactory;




    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanPostProcessor(AopPostProcessor processor) {
        this.beanFactory.registerBeanPostProcessor(processor);
    }

    /**
     * 根据前缀返回不同的解析对象
     * @param localtions
     * @return
     * @throws Exception
     */
    @Override
    public Resource getResource(String localtions) throws Exception {
        if(localtions.contains(":")){
            String[] split = localtions.split(":");
            StringBuilder sb = new StringBuilder();
            sb.append(split[1]);
            for(int i=2;i<split.length;i++){
                sb.append(":" + split[i]);
            }
            if("url".equals(split[0])){
                return new URLResource(sb.toString());
            }else if("classpath".equals(split[0])){
                return new ClasspathResource(null, sb.toString(), null);
            }else if("file".equals(split[0])){
                return new FileSystemResource(sb.toString());
            }
        }else {
            throw new Exception("传入配置文件loc格式出错");
        }
        return null;
    }
}