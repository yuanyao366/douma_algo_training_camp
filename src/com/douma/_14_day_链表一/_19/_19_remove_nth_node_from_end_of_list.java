package com.douma._14_day_链表一._19;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _19_remove_nth_node_from_end_of_list {
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        // 1. 计算链表长度
        int length = 0;
        ListNode curr = dummyNode;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        // 2. 找到倒数第 n 个节点的前一个节点
        ListNode prev = dummyNode;
        for (int i = 1; i < length - n; i++) {
            prev = prev.next;
        }
        // 3. 删除倒数第 n 个节点
        ListNode delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }

    // 快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        // fast 先走 n + 1
        while (n-- >= 0) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode delNode = slow.next;
        slow.next = delNode.next;
        delNode.next = null;

        return dummyNode.next;
    }
}
