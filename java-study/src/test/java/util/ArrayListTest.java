package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description: ArrayList 的测试类
 * Datetime:    2021/3/11   下午1:32
 * Author:   王震
 */
@Slf4j
public class ArrayListTest {

    @Test
    public void test01(){
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==4){
                iterator.remove();
            }
        }
    }

}
