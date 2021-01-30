package com.wangzhen.myspring.context.scanner;

import com.wangzhen.myspring.bean.beandefinition.BeanDefinition;
import com.wangzhen.myspring.bean.beandefinition.BeanDefinitionRegistry;
import com.wangzhen.myspring.bean.beandefinition.impl.DefaultBeanDefinition;
import com.wangzhen.myspring.bean.factory.BeanFactory;
import com.wangzhen.myspring.ioc.annotation.Component;
import com.wangzhen.myspring.ioc.annotation.ComponentScan;
import com.wangzhen.myspring.ioc.annotation.Controller;
import com.wangzhen.myspring.ioc.annotation.Service;
import com.wangzhen.myspring.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Description:
 * Datetime:    2020/11/2   4:52 下午
 * Author:   王震
 */
public class ClassPathBeanDefinitionScanner {
    private BeanDefinitionRegistry registry;
    List<Class<?>> classList = new ArrayList<>();


    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void scan(Class<?>... configClass){
        for (Class<?> beanClass : configClass) {
            DefaultBeanDefinition bd = new DefaultBeanDefinition();
            bd.setClazz(beanClass);
            bd.setBeanName(beanClass.getName());
            registry.register(bd,toLowerCaseFirstOne(beanClass.getSimpleName()));
            // 如果这个类上有 @ComponentScan  需要扫描该包下面的所有的类
            if(beanClass.isAnnotationPresent(ComponentScan.class)){
                ComponentScan annotation = beanClass.getAnnotation(ComponentScan.class);
                String[] basePackages = annotation.value();
                scan(basePackages);
            }
        }
        // 将扫描到的所有的类都注册到 beanfactory中去。
        for (Class<?> beanClass : classList) {
            // 如果是以下的注解那么将类注册到 beanFactory 中去
            if(beanClass.isAnnotationPresent(Component.class)||
                    beanClass.isAnnotationPresent(Controller.class)||
                    beanClass.isAnnotationPresent(Service.class)
            ){
                DefaultBeanDefinition bd = new DefaultBeanDefinition();
                bd.setClazz(beanClass);
                bd.setBeanName(beanClass.getSimpleName());
                registry.register(bd,toLowerCaseFirstOne(beanClass.getSimpleName()));
            }

        }

    }

    public  String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public void scan(String... basePackages){
        for (String basePackage : basePackages) {
            getClassListByPackageName(basePackage);
        }
    }


    public void getClassListByPackageName(String packageName) {

        try {
            Enumeration<URL> resources = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()){
                URL url = resources.nextElement();
                if(url!=null){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        // 若在 class 目录中，则执行添加类操作
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(packagePath,packageName);
                    }else if(protocol.equals("jar")){ // 如果是jar 文件
                        // 若在 jar 包中，则解析 jar 包中的 entry
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            // 判断该 entry 是否为 class
                            if (jarEntryName.endsWith(".class")) {
                                // 获取类名
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                // 执行添加类操作
                                doAddClass(className);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }

    public void addClass(String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // 过滤所有文件，如果文件是以class 结尾，或者文件是目录
                return (file.isFile()&&file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if(file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtils.isNotEmpty(className)){
                    // 带包名的类名 形如 con.wangzhen.spring.test
                    className = packageName+"."+className;
                }
                doAddClass(className);
            }else if(file.isDirectory()){
                String subPackagePath = null;
                if(StringUtils.isNotEmpty(packagePath)){
                    subPackagePath = packagePath+"/"+file.getName();
                }
                String subPackageName = null;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackageName = packageName+"."+file.getName();
                }
                addClass(subPackagePath,subPackageName);
            }
        }


    }

    public void doAddClass(String className){
        try {
            Class<?> loadClass = ClassUtil.getClassLoader().loadClass(className);
            // 将加载到的类放入到 list 中
            classList.add(loadClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
