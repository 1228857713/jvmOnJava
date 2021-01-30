package com.wangzhen.springx.core.impl;

import com.wangzhen.springx.core.ClassScanner;
import com.wangzhen.springx.util.ClassUtil;
import com.wangzhen.springx.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Description: 默认包扫描器,扫描指定 包名下的类
 * Datetime:    2020/10/25   12:17 上午
 * Author:   王震
 */
public class DefaultClassScanner implements ClassScanner {


    public static void main(String[] args) {
        ClassScanner scanner = new DefaultClassScanner();
        scanner.getClassListByPackage("com.wangzhen.springx");
    }

    @Override
    public List<Class<?>> getClassListByPackage(String packageName) {
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
        return classList;
    }

    public void addClass(String packagePath,String packageName){
        System.out.println(packageName);
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
                if(StringUtil.isNotEmpty(className)){
                    // 带包名的类名 形如 con.wangzhen.spring.test
                    className = packageName+"."+className;
                }
                doAddClass(className);
            }else if(file.isDirectory()){
                String subPackagePath = null;
                if(StringUtil.isNotEmpty(packagePath)){
                     subPackagePath = packagePath+"/"+file.getName();
                }
                String subPackageName = null;
                if(StringUtil.isNotEmpty(packageName)){
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

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
