package util.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: wangzhen
 * @date: 2021/3/17 13:24
 * @desc: 原子引用类的测试，主要测试 cas中ABA的问题
 */
@Slf4j
public class AtomicReferenceTest {
    static Node<Integer> head,tail;
    static class Node<T>{
        T value;
        Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }

    @Before
    public void init(){
        tail = head = new Node<>(0);
        for (int i = 0; i < 5; i++) {
            Node<Integer> node = new Node<>(i);
            tail.next = node;
            tail = node;
        }
    }


    @Test
    public void casTest(){
        AtomicReference<Node<Integer>> reference = new AtomicReference<>(head);

    }
}