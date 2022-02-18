package com.douma._15_day_链表二._2;

import com.douma._15_day_链表二.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _2_add_two_numbers {
    /* 2. 两数相加
    给你两个 非空 的链表，表示两个非负的整数。
    它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

    请你将两个数相加，并以相同形式返回一个表示和的链表。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 课程 A -》 链表 -> 基础篇
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;
            curr.next = new ListNode(sum % 10);
            // bug 修复：视频中忘了加上这一步
            curr = curr.next;
            carry = sum / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) curr.next = new ListNode(carry);
        return dummy.next;
    }
}
