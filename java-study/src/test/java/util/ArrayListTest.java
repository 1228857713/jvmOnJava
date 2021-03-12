package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description: ArrayList 的测试类
 * Datetime:    2021/3/11   下午1:32
 * Author:   王震
 */
@Slf4j
public class ArrayListTest {



    @Test
    public void test01(){
        String []names = {"wangzhen","郭德纲","haha"};
        List<String> strings = Arrays.asList(names);

        // 修改list 的值数组对应的值也会更改
        strings.set(0,"王震");
        log.info(names[0]);

        strings.add("hello");
        strings.remove(2);
        strings.clear();
    }


    /**
     *  在遍历集合的时候不能对其进行操作否则会抛出 ConcurrentModificationException
     */
    @Test
    public void test02(){
        ArrayList<String> list = new ArrayList<String>(10);
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        for (String s : list) {
            if("two".equals(s)){
                list.remove("two");
            }
        }
        System.out.println(list);

    }

    /**
     *  正确的迭代数组的方式
     *
     */
    @Test
    public void test03(){
        List<String> list = new ArrayList<String>(10);
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals("two")){
                // iterator.remove的的时候回给 excepectModCount 重新赋值，所以在检查的是后续不会报错
                iterator.remove();
            }
        }

        System.out.println(list);

    }

    /**
     * 在遍历集合的时候不能对其进行操作否则会抛出 ConcurrentModificationException
     * 这样却不会报错，具体请看 ArrayList.hasNext()方法
     */
    @Test
    public void test04(){
        ArrayList<String> list = new ArrayList<String>(10);
        list.add("one");
        list.add("two");
        list.add("three");
        for (String s : list) {
            if("two".equals(s)){
                list.remove("two");
            }
        }
        System.out.println(list);

    }


    /**
     * 在遍历集合的时候不能对其进行操作否则会抛出 ConcurrentModificationException
     * 对于线程安全的类，其不会有问题
     */
    @Test
    public void test05(){
        List<String> list = new CopyOnWriteArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        for (String s : list) {
            if("two".equals(s)){
                list.remove("two");
            }
        }
        System.out.println(list);

    }



}
