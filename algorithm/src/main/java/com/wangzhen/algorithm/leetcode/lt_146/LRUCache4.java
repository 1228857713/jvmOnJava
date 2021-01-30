package com.wangzhen.algorithm.leetcode.lt_146;

import java.util.HashMap;

/**
 * Description:  Least Recently Used 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
 * Datetime:    2020/9/27   2:07 下午
 * Author:   王震
 */
public class LRUCache4 {

    HashMap<Integer,Integer> map;
    DList<Integer,Integer> dList;

    int capacity;



    public LRUCache4(int capacity) {
        this.capacity = capacity;
        map = new HashMap();
        dList = new DList();
    }


    public int get(int key) {
        if(map.containsKey(key)){
            int value = map.get(key);
            dList.del(key);
            dList.put(key,value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.put(key,value);
            dList.del(key);
            dList.put(key,value);
        }else{
            // 如果集合已经满了
            if(dList.size==capacity){
                // 移除第一个key
                map.remove(dList.getFirst());
                map.put(key,value);
                dList.del(dList.getFirst());
                dList.put(key,value);
            }else{
                map.put(key,value);
                dList.del(key);
                dList.put(key,value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache4 cache = new LRUCache4( 2 /* 缓存容量 */ );
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

class DList<K,V> {
    int size;
    Node<K,V> head;
    Node<K,V> tail;
    // 尾插法，新的节点放在尾部
    public void put(K key,V value){

        Node newNode = new Node(key,value);
        if(tail==null){
            head = tail = newNode;
            size++;
            return;
        }
        Node t = tail;
        newNode.prev = t;
        newNode.next = null;
        t.next= newNode;
        tail = newNode;
        size++;
    }


    public K getFirst(){
        if(head!=null){
            return head.key;
        }
        return null;
    }


    public V get(K key){
        Node<K,V> x = new Node(key,null);
        return findNode(x).value;
    }

    public Node<K,V> findNode(Node node){
        Node<K,V> x = head;
        while (x!=null){
            if(x.key==node.key){
                return x;
            }
            x = x.next;
        }
        return null;
    }

    public void del(K key){
        Node x = new Node(key,null);
        del(x);
    }
    // 删除一个 node 节点
    public void del(Node node){
        Node x = findNode(node);
        if(x==null){
            return;
        }
        Node prevNode = x.prev;
        Node nextNode = x.next;
        // 表示只有一个节点删除掉了
        if(prevNode==null&&nextNode==null){
            head = tail = null;
            size--;
            return;
        }
        // 表示删除的是头结点
        if(prevNode==null){
            nextNode.prev=null;
            head=nextNode;
            size--;
            return;
        }
        // 表示删除的是尾节点
        if(nextNode==null){
            prevNode.next=null;
            tail = prevNode;
            size--;
            return;
        }
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
        size--;
        return;

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
}


