package com.wangzhen.jvm;


import com.wangzhen.jvm.classfile.classPath.ClassPath;
import com.wangzhen.jvm.instructions.Interpreter;
import com.wangzhen.jvm.runtimeData.ZFrame;
import com.wangzhen.jvm.runtimeData.ZThread;
import com.wangzhen.jvm.runtimeData.helap.ZClass;
import com.wangzhen.jvm.runtimeData.helap.ZClassLoader;
import com.wangzhen.jvm.runtimeData.helap.ZMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.util.Arrays;


/**
 *  使用说明：
 *  1.启动参数
 *   -cp jvmx/target/classes/  com.wangzhen.jvm.classfile.App
 *
 *   -cp jvmx/target/test-classes/  com.wangzhen.jvm.classfile.App
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        // https://blog.csdn.net/yamaxifeng_132/article/details/87822812
        Options options = new Options();
        options.addOption("h", "help", false, "打印帮助信息");
        options.addOption("v", "version", false, "显示当前jdk 版本");
        options.addOption("jar",true,"执行一个jar");
        //options.addOption("cp","classpath",true,"指定类路径");
        Option cpOption = OptionBuilder
                .withArgName("args")
                .withLongOpt("classpath")
                .hasArgs()
                .withDescription("制定类路径")
                .create("cp");
        options.addOption(cpOption);
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cli = parser.parse(options, args);
            if (cli.hasOption("h")){
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("Options", options);
                return;
            }
            if(cli.hasOption("v")){
                log.info("jdk1.8");
                return;
            }
            if(cli.hasOption("jar")){
                String []jarParameters = cli.getOptionValues("jar");
                log.info(String.valueOf(Arrays.asList(jarParameters)));
                return;
            }
            // java -jar jvm.jar -cp classes/com/wangzhen/jvm/ com.wangzhen.jvm.classfile.App.class
            // -cp classes/com.wangzhen.jvm.classfile.App com.wangzhen.jvm.classfile.App.class
            if(cli.hasOption("cp")){
                String []cpParameters = cli.getOptionValues("cp");
                DirEntry dirEntry = new DirEntry(cpParameters[0]);
//                byte [] classFileData = dirEntry.readClass(cpParameters[1]);
//                ClassFile classFile = new ClassFile(classFileData);
//                System.out.println(classFile.toString());
                startJvm(cpParameters);
                return;
            }

            // 如果上述参数都没有那么直接将启动class todo:写的太垃圾了
            String[] strings =new String[2];
            strings[0]="";
            strings[1]=cli.getArgs()[0];
            startJvm(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void startJvm(String [] cpParameters) throws NoSuchMethodException {
        ClassPath classPath = new ClassPath("",cpParameters[0]);
        // 创建classLoader 加载基本属性类
        ZClassLoader classLoader = new ZClassLoader(classPath);
        // 这里打断点，可以看到直接想要的类的加载过程
        ZClass zClass =  classLoader.loadClass(cpParameters[1]);
        ZMethod zMethod = zClass.getMainMethod();
        ZThread thread = new ZThread();
        ZFrame frame = new ZFrame(thread,zMethod);
        thread.pushFrame(frame);
        Interpreter.loop(thread);

    }



}
