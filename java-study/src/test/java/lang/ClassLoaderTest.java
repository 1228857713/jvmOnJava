package lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: wangzhen
 * @date: 2021/3/24 9:58
 * @desc: 测试类加载器
 */
@Slf4j
public class ClassLoaderTest {

    @Test
    public void test1(){
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Class<?> stringClass = myClassLoader.loadClass("java.lang.String");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static class MyClassLoader extends ClassLoader{

    }
}