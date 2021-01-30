package com.wangzhen.algorithm.leetcode.lt_146;

import java.util.HashMap;

/**
 * Description:
 * Datetime:    2020/9/28   8:47 上午
 * Author:   王震
 */
public class LRUCache {
    HashMap<Integer,Node<Integer,Integer>> map;
    int capacity ;
    Node head; // dummy 节点
    Node tail; // dummy 节点

    public LRUCache(int capacity) {
        map = new HashMap<>();
        // dummy 节点 方便处理 列表
        head = new Node(-1,-1);
        // dummy 节点 方便处理 列表
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        // 如果
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer,Integer> node = map.get(key);
        // 移除这个节点
        node.prev.next=node.next;
        node.next.prev = node.prev;
        // 将这个节点加到节点尾部
        moveToTail(node);
        return node.value;
    }
    // 将这个节点 加到 节点的尾部
    public void moveToTail(Node node){
        tail.prev.next=node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    public void put(int key, int value) {
        // 代表map 里面 已经有了 这个key 而且已经把他加到 list 的尾部了
        if(get(key)!=-1){
            // 因为 key 都是一样的 直接把 value 替换掉即可
            map.get(key).value = value;
            //tail.prev.value =value;
            return;
        }
        // 如果不存在 这个节点 需要新加 ，那么就要考虑容量的问题
        Node newNode = new Node(key,value);
        // 容量已经满了
        if(map.size()==capacity){
            // 移除头节点
            Node next = head.next;
            next.next.prev = head;
            head.next = next.next;
            map.remove(next.key);
            map.put(key,newNode);
            moveToTail(newNode);
        }else if(map.size()<capacity){
            // 直接将新的节点 加入到 列表结尾
            moveToTail(newNode);
            map.put(key,newNode);
            return;
        }

    }

    class Node<K,V>{
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 3 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));//4
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(2));//2
        System.out.println(cache.get(1));//-1
        cache.put(5, 5);
        System.out.println(cache.get(1));//-1
        System.out.println(cache.get(2));//2
        System.out.println(cache.get(3));//3
        System.out.println(cache.get(4));//-1
        System.out.println(cache.get(5));//5
    }
}
