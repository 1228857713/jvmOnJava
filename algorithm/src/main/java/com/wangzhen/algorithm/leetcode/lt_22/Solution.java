package com.wangzhen.algorithm.leetcode.lt_22;

/**
 * Description: 剑指 Offer 22. 链表中倒数第k个节点
 * Datetime:    2020/10/3   10:07 下午
 * Author:   王震
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        if(fast==null){
            return slow;
        }
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow.next;
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
