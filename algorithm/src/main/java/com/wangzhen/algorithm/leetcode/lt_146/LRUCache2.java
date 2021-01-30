package com.wangzhen.algorithm.leetcode.lt_146;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: lru 使用单列表+ map 实现
 * Datetime:    2020/9/27   8:56 下午
 * Author:   王震
 */
public class LRUCache2 {

    Map<Integer,Node> map ;
    Node<Integer,Integer> head ;
    Node<Integer,Integer> tail ;
    int capacity;

    public LRUCache2(int capacity) {
       map = new HashMap<>(capacity);
       head = new Node<>(-1,-1); // dummy
       tail = new Node<>(-1,-1); // dummy
        head.next=tail;
        tail.prev=head;
        this.capacity=capacity;
    }

    public int get(int key) {
        // 不包含直接返回 -1
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer,Integer> x = map.get(key);

        moveToTail(x);
        return x.value;
    }

    public void put(int key, int value) {
        // 表示存在这个值,get 方法会将其移动到最后的节点
        if(get(key)!=-1){
            tail.prev.value=value;
            map.put(key,tail.prev);
            return;
        }
        // 否则需要新建一个key
        Node newNode = new Node(key,value);
        map.put(key,newNode);
        if(map.size()==capacity){
            map.remove(head.next.key);
            Node next = head.next;
            head=next.next;
            next.prev=head;
            next=null;
        }
        addNodeToTail(newNode);
    }

    public void addNodeToTail(Node node){
        tail.prev.next=node;
        node.prev=tail.prev;
        node.next=tail;
        tail.prev=node;
    }


    // 将节点移动到末尾
    public void moveToTail(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next=nextNode;
        nextNode.prev= prevNode;
        node.prev=tail.prev;
        node.next=tail;
        tail.prev.next=node;
        tail.prev=node;
    }

    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2( 2 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2)); // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3));// 返回  3
        System.out.println(cache.get(4));  // 返回  4
    }
}