package com.wangzhen.algorithm.tree;

/**
 * Description:
 * Datetime:    2020/9/12   5:35 下午
 * Author:   王震
 */
public class RedBlackTree {
    public int red = 0;
    public int black =1;
    public Node root;

    class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        public Node(int data){
            this.data = data;
        }
    }

    public void insert(Node root, int data){

        if(data>=root.data){
            if(root.right == null){
                Node newNode = new Node(data);
                root.right = newNode;
            }else {
                insert(root.right,data);
            }
        }else {
            if(root.left == null){
                Node newNode = new Node(data);
                root.left = newNode;
            }else {
                insert(root.left,data);
            }
        }
    }

    public void leftRotate(){
        
    }
}
