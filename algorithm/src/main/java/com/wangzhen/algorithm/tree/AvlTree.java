package com.wangzhen.algorithm.tree;




/**
 * Description:  平衡二叉树
 * Datetime:    2020/9/13   2:50 下午
 * Author:   王震
 */
public class AvlTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        int [] array = {4,3,6,5,7,8};
        for (int i = 0; i < array.length; i++) {
            tree.add(new Node(array[i]));
        }
        //tree.lastOrder();
        System.out.println(tree.getRoot().height());
        System.out.println(tree.getRoot().left.height());
        System.out.println(tree.getRoot().right.height());
    }

    public AvlTree() {
    }

    public AvlTree(int []arrays) {
        for (int i = 0; i < arrays.length; i++) {
            add(new Node(arrays[i]));
        }
    }



    // 添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;// 如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 查找结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 前序遍历
    public void preOrder() {
        if (root != null) {
            root.preorder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }


    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    // 后序遍历
    public void lastOrder() {
        if (root != null) {
            root.lastorder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }




     static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }

        public int leftHeight(){
            return left == null ? 0 : left.height();
        }

        public int rightHeight(){
            return right == null ? 0 :right.height();
        }

        // 返回 以当前节点为根节点的数的高度
        public int height(){
            return Math.max(this.left==null ? 0:this.left.height(),this.right==null?0 :this.right.height())+1;
        }

        public Node search(int value){
            if(this.data ==value){
                return this;
            }

            if(value>data){
                return right == null ? null: search(right.data);
            }else {
                return left == null ? null :search(left.data);
            }
        }

        public void add(Node node){
            if(node == null){
                return;
            }
            if(node.data>this.data){
                if (right == null) {
                    right = node;
                } else {
                    right.add(node);
                }
            }else{
                if(left == null){
                    left = node;
                }else {
                    left.add(node);
                }
            }
        }




        // 前序遍历
        public void preorder(){
            System.out.println(this.data);
            if (this.left != null) {
                this.left.preorder();
            }

            if (this.right != null) {
                this.right.preorder();
            }
        }

        // 中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this.data);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        // 后序遍历
        public void lastorder(){
            if (this.right != null) {
                this.right.lastorder();
            }
            if (this.left != null) {
                this.left.lastorder();
            }
            System.out.println(this.data);


        }
    }

}


