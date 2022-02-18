package com.douma._14_day_链表一._876;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _876_middle_of_the_linked_list {
    // 快慢指针
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 普通解法
    public ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) return head;
        // 1. 计算链表的长度
        int length = 0;
        ListNode curr = head;
        while (curr != null) { // O(n)
            length++;
            curr = curr.next;
        }
        // 2. 找到链表的中间节点
        for (int i = 0; i < length / 2; i++) { // O(n/2)
            head = head.next;
        }

        return head;
    }
}
