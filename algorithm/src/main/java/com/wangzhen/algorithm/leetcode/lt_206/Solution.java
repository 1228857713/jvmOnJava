package com.wangzhen.algorithm.leetcode.lt_206;

/**
 * Description: 206. 反转链表
 * Datetime:    2020/10/3   9:57 下午
 * Author:   王震
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null)
            return null;
        if(head.next==null){
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}