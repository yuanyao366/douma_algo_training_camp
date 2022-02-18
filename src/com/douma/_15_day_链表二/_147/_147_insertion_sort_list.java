package com.douma._15_day_链表二._147;

import com.douma._15_day_链表二.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _147_insertion_sort_list {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            if (curr.val >= prev.val) {
                prev = curr;
                curr = curr.next;
            } else {
                // 找到小于 curr.val 的最大的节点
                ListNode p = dummyNode;
                // 说明：这里的 p.next 不可能为空
                // 因为 p 从头开始，最远可以到达的节点是 curr 的前一个节点
                // 所以 p.next 不可能为 null，我这里加上 p.next 的判空，是我个人的习惯哟~
                while (p.next != null && p.next.val < curr.val) {
                    p = p.next;
                }
                // 将 curr 插入到 p 和 p.next 之间
                prev.next = curr.next;
                curr.next = p.next;
                p.next = curr;

                curr = prev.next;
            }
        }
        return dummyNode.next;
    }
}
