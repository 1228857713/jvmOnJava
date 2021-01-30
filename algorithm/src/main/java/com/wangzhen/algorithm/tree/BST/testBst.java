package com.wangzhen.algorithm.tree.BST;


import org.junit.Test;

/**
 * Description: 测试二叉树的一些性质
 * Datetime:    2020/9/24   8:05 下午
 * Author:   王震
 */
public class testBst {
    BST bt = null;
    public void createBst(){
        bt = new BST();
        bt.insert(50);
        bt.insert(20);
        bt.insert(80);
        bt.insert(10);
        bt.insert(30);
        bt.insert(60);
        bt.insert(90);
        bt.insert(25);
        bt.insert(85);
        bt.insert(100);
    }

    @Test
    public void testPreOrder(){
        createBst();
        bt.preOrder(bt.root);
        System.out.println(bt.list);
        bt.list.clear();
       // bt.preOrder();
        System.out.println(bt.list);
    }

    @Test
    public void testInfixOrder(){
        // 二叉搜索树 的中序遍历是 数组的正序排列
        createBst();
        bt.infixOrder(bt.root);
        System.out.println(bt.list);
    }

    @Test
    public void testPostOrder(){
        createBst();
        bt.postOrder(bt.root);
        System.out.println(bt.list);

    }

    @Test
    public void testMaxAndMin(){
        createBst();
        System.out.println(bt.findMax().getValue());
        System.out.println(bt.findMin().getValue());
    }

    @Test
    public void testDelete(){
        createBst();
        bt.delete(10);
    }
}
