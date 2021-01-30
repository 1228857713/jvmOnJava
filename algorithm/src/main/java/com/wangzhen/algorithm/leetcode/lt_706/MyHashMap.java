package com.wangzhen.algorithm.leetcode.lt_706;

import java.util.LinkedList;

/**
 * Description:  706. 设计哈希映射
 * Datetime:    2020/10/3   7:05 下午
 * Author:   王震
 */
class MyHashMap {

    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(10001, 10001);
        hashMap.put(1, 2);
        System.out.println(hashMap.get(1));//1
        System.out.println(hashMap.get(10001));// -1
        hashMap.put(20001, 20001); // 更新已有的值
        System.out.println(hashMap.get(2));//1
        hashMap.remove(20001);// 删除键为2的数据
        System.out.println(hashMap.get(2)); // -1

    }
    Node [] arrays ;
    int hashFactor = 2069;

    public int getHashKey(int key){
        return  key%hashFactor;
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        // 总的操作数目是这个 所以hashmap 对这个数取余
        arrays = new Node[hashFactor];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hashkey = getHashKey(key);
        Node node = new Node(key,value);
        // 如果节点为空
        if(arrays[hashkey]==null){
            arrays[hashkey] = node;
        }else{
            insertTail(arrays[hashkey],node);
        }
    }
    // 尾插法
    public void insertTail(Node head,Node newNode){
        Node tail = null;

        for (Node node = head;node !=null; node = node.next){
            if(node.key == newNode.key){
                node.value = newNode.value;
                return;
            }
            tail = node;
        }

        tail.next = newNode;
        newNode.next = null;
        newNode.prev = tail;
    }


    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hashKey = getHashKey(key);
        if(arrays[hashKey]==null){
            return  -1;
        }else{
            Node head = arrays[hashKey];
            for (Node node = head;node!=null;node = node.next){
                if(key == node.key){
                    return node.value;
                }
            }
        }
        return  -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hashKey = getHashKey(key);
        Node head = arrays[hashKey];
        if(head == null){
            return;
        }
        if(head.key == key){
            if(head.next==null){
                arrays[hashKey] = null;
            }else {
                arrays[hashKey] = head.next;
            }
        }
        for (Node node = head.next;node!=null;node = node.next){
            if(node.key == key){
                if(node.next!=null){
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }else {
                    node.prev.next = null;
                    node = null;
                }
                return;
            }
        }
    }

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
