package com.wangzhen.myspring.bean.factory.impl;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinition;
import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.bean.factory.BeanFactory;
import com.wangzhen.myspring.bean.postprocessor.AopPostProcessor;
import com.wangzhen.myspring.ioc.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 默认Bean 工厂
 *              实现Closeable接口用于销毁对象
 * Datetime:    2020/11/1   8:16 下午
 * Author:   王震
 */
public class DefaultBeanFactory implements BeanFactory , BeanDefinitionRegistry , Closeable {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 使用ConcurrentHashMap 应对 并发情况
    private Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();

    private Map<String,Object> beanMap = new ConcurrentHashMap<>();

    // 记录正在创建的 bean
    private ThreadLocal<Set<String>> initialedBeans = new ThreadLocal<>();

    // 记录观察者
    private List<AopPostProcessor> aopPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        return doGetBean(beanName);

    }


    // 真正实例化bean的过程
    public Object doGetBean(String beanName) throws Exception {
        if(!bdMap.containsKey(beanName)){
            logger.info(beanName+"不存在");
            return null;
        }
        //得到当前线程正在创建的bean
        Set<String> initingBeans = this.initialedBeans.get();
        if(initingBeans==null){
            initingBeans = new HashSet<>();
            this.initialedBeans.set(initingBeans);
        }
        // 这样就能检测循环依赖嘛
        if(initingBeans.contains(beanName)){
            throw new Exception("检测到"+beanName+"存在循环依赖:"+initingBeans);
        }
        // 表明正在创建该bean
        initingBeans.add(beanName);


        // 如果该实例已经被创建过了 直接返回
        Object instance = beanMap.get(beanName);
        if(instance!=null){
            return instance;
        }
        //todo  不存在beanMap 定义 则进行创建
        if(!this.bdMap.containsKey(beanName)){
            logger.info("不存在名为：[" + beanName + "]的bean定义,即将进行创建");
        }

        BeanDefinition bd = bdMap.get(beanName);
        Class<?> beanClass = bd.getBeanClass();

        if(beanClass!=null){
            instance = createBeanByConstruct(bd);
            if(instance==null){
                instance = beanClass.newInstance();
            }
        }else if(instance == null && StringUtils.isNotBlank(bd.getStaticCreateBeanMethod())){
            // 通过工厂类创建bean
            instance = createBeanByFactoryMethod(bd);
        }
        // 初始化 instance
        doInit(bd,instance);
        //添加属性依赖
        this.parsePropertyValues(bd,instance);
        // 创建完成 移除记录
        initingBeans.remove(beanName);
        // 添加 aop 处理
        instance = applyAopBeanPostProcessor(instance,beanName);

        // 如果是单例的话 那么直接 存放到 map 中后直接获取
        if(instance!=null&&bd.isSingleton()){
            beanMap.put(beanName,instance);
        }
        return instance;
    }

    // 通过动态代理对符合条件的类 进行增强
    private Object applyAopBeanPostProcessor(Object instance, String beanName) throws Exception {
        for (AopPostProcessor aopPostProcessor : aopPostProcessors) {
            instance = aopPostProcessor.postProcessWeaving(instance, beanName);
        }
        return instance;
    }

        // todo 解析 bean 的属性依赖
    private void parsePropertyValues(BeanDefinition bd, Object instance) throws Exception {
        Class<?> beanClass = bd.getBeanClass();
        // 得到该类上的所有的所有属性
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            // 设置可以访问否则private 的属性会报错
            field.setAccessible(true);
            // 如果这个属性上有 Autowired 注解那么代表该类需要被注入
            if(field.isAnnotationPresent(Autowired.class)){
                // 得到该属性的类型和名字
                Class type = field.getType();
                String name = field.getName();
                // 查看当前的beanMap 中是否已经实例化该对象
                Object o = this.beanMap.get(name);
                if(o!=null){
                    // 该属性设置实例推向，其中 instnce 是持有该属性的对象
                    // o 是注入的对象
                    field.set(instance,o);
                }else{
                    // 如果没有那么只能重新生成了
                    Object bean = getBean(name);
                    if(bean==null){
                        throw new Exception(beanClass.getName()+"."+field.getName()+"属性注入失败找不到相应的注入类");
                    }else{
                        field.set(instance,bean);
                    }

                }
            }
        }
    }


    // 初始化对象
    private void doInit(BeanDefinition bd, Object instance) {
        Class<?> beanClass = instance.getClass();
        if(StringUtils.isNoneBlank(bd.getBeanInitMethodName())){
            try {
                Method method = beanClass.getMethod(bd.getBeanInitMethodName(), null);
                method.invoke(instance,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过工厂方法创建类
     */
    private Object createBeanByFactoryMethod(BeanDefinition bd) {
        Object isntance = null;
        try{
            // 根据工厂类名 获取工厂对象
            Object factory = doGetBean(bd.getBeanFactory());
            Method method = factory.getClass().getMethod(bd.getCreateBeanMethod());
            isntance = method.invoke(factory,null);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return isntance;
    }

    /**
     * todo 通过构造方法创建对象
     * 这里直接通过 class 创建出对象
     * @param bd
     * @return
     */
    private Object createBeanByConstruct(BeanDefinition bd) {
        Object o = null;
        try {
             o = bd.getBeanClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    private Object createBeanByStaticFactoryMethod(BeanDefinition bd) {
        Object instance = null;
        Class<?> beanClass = bd.getBeanClass();
        try {
            Method method = beanClass.getMethod(bd.getStaticCreateBeanMethod(), null);
           // method.invoke(beanClass,null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public void registerBeanPostProcessor(AopPostProcessor processor) {
        this.aopPostProcessors.add(processor);
    }

    @Override
    public String[] getBeanNameForType(Class<?> tClass) {
        // todo
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansForType(Class<?> clazz) {

        //todo
        return null;
    }

    @Override
    public Class getType(String beanName) {
        BeanDefinition o = (BeanDefinition) beanMap.get(beanName);
        return o.getBeanClass();
    }

    @Override
    public void register(BeanDefinition bd, String beanName) {
        Assert.assertNotNull("bd 不能为空",bd);
        Assert.assertNotNull("beanName 不能为空",beanName);
        if(bdMap.containsKey(beanName)){
            logger.info("["+beanName+"]已经存在，将覆盖这个beanName");
        }

        if(!bd.validate()){
            logger.error("BeanDefinition不合法");
            return;
        }
        bdMap.put(beanName,bd);


    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return bdMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        if(!bdMap.containsKey(beanName)){
            logger.info("[" + beanName + "]不存在");
            return null;
        }
        return bdMap.get(beanName);
    }

    // 销毁方法
    @Override
    public void close() throws IOException {
        Set<Map.Entry<String, BeanDefinition>> entries = bdMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            BeanDefinition bd = entry.getValue();
            String beanDestoryMethodName = bd.getBeanDestoryMethodName();
            try {
                Method destoryMethod = bd.getBeanClass().getDeclaredMethod(beanDestoryMethodName, null);
                // 调用类自己的 destory 方法 关闭
                destoryMethod.invoke(bd,null);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
