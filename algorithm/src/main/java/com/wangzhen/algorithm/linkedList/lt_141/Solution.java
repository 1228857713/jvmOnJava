package com.wangzhen.algorithm.linkedList.lt_141;

import com.wangzhen.algorithm.linkedList.ListNode;

/**
 * Description:
 * Datetime:    2020/11/23   11:55 下午
 * Author:   王震
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode fast,slow;
        fast = slow = head;
        while (fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast =fast.next.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
}

