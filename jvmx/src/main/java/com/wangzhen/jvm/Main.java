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
 *
 *  该方法是jvmx 的启动方法
 *  1.启动参数：
 *    -cp jvmx/target/classes/com/wangzhen/jvm  app
 *    目前只实现了最简单的功能，就是传递一个文件路径，和要执行的文件名，给我们的虚拟机。然后我们自定义的类加载器去加载该类去执行。
 *    重点是观察jvm 指令执行的步骤和流程。
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        // https://blog.csdn.net/yamaxifeng_132/article/details/87822812
        Options options = new Options();
        // 目前实现三个参数  -h  -v -jar -cp  核心功能通过-cp 实现
        options.addOption("h", "help", false, "打印帮助信息");
        options.addOption("v", "version", false, "显示当前jdk 版本");
        options.addOption("jar",true,"执行一个jar");
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
            }
            if(cli.hasOption("v")){
                log.info("jdk1.8");
            }
            if(cli.hasOption("jar")){
                String []jarParameters = cli.getOptionValues("jar");
                log.info(String.valueOf(Arrays.asList(jarParameters)));
            }

            // java -jar jvm.jar -cp classes/com/wangzhen/jvm/ App.class
            // -cp classes/App app.class
            if(cli.hasOption("cp")){
                String []cpParameters = cli.getOptionValues("cp");
                DirEntry dirEntry = new DirEntry(cpParameters[0]);
//                byte [] classFileData = dirEntry.readClass(cpParameters[1]);
//                ClassFile classFile = new ClassFile(classFileData);
//                System.out.println(classFile.toString());
                startJvm(cpParameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void startJvm(String [] cpParameters) throws NoSuchMethodException {
        ClassPath classPath = new ClassPath("",cpParameters[0]);
        // 创建classLoader 加载基本属性类
        ZClassLoader classLoader = new ZClassLoader(classPath);
        ZClass zClass =  classLoader.loadClass(cpParameters[1]);
        ZMethod zMethod = zClass.getMainMethod();
        ZThread thread = new ZThread();
        ZFrame frame = new ZFrame(thread,zMethod);
        thread.pushFrame(frame);
        Interpreter.loop(thread);

    }



}
