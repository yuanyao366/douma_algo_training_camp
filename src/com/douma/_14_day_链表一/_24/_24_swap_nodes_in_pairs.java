package com.douma._14_day_链表一._24;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _24_swap_nodes_in_pairs {
    /* 24. 两两交换链表中的节点
    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

    输入：head = 1->2->3->4->5->NULL
    输出：2->1->4->3->5->NULL
     */

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        ListNode first = head;
        ListNode second = head.next;
        while (second != null) {
            ListNode next = second.next;
            prev.next = second;
            second.next = first;
            first.next = next;

            prev = first;
            first = next;
            if (first == null) break;
            second = first.next;
        }
        return dummyNode.next;
    }
}
