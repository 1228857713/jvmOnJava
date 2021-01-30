package com.wangzhen.algorithm.linkedList.lt_876;

import com.wangzhen.algorithm.linkedList.ListNode;

/**
 * Description:
 * Datetime:    2020/11/24   7:05 下午
 * Author:   王震
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        if(head == null)
            return null;
        ListNode slow,fast;
        fast = slow = head;
        while (fast.next!=null&&fast.next.next!=null){
            fast =fast.next.next;
            slow = slow.next;
        }
        if(fast.next==null){
            return slow;
        }else{
            return slow.next;
        }

    }
}