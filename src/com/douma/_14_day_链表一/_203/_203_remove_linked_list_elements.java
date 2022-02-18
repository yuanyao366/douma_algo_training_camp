package com.douma._14_day_链表一._203;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _203_remove_linked_list_elements {
    public ListNode removeElements1(ListNode head, int val) {
        // 1. 删除表头元素
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) return null;

        // 2. 删除非表头元素
        ListNode curr = head.next;
        ListNode prev = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = prev.next;
        }

        return head;
    }

    // 使用虚拟节点统一删除头节点和非头节点的逻辑
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode curr = head;
        ListNode prev = dummyNode;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = prev.next;
        }

        return dummyNode.next;
    }
}
