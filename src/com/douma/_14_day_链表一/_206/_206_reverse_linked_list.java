package com.douma._14_day_链表一._206;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _206_reverse_linked_list {
    // 递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // 迭代
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
