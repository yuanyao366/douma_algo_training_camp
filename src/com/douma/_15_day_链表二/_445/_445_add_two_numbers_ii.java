package com.douma._15_day_链表二._445;

import com.douma._15_day_链表二.ListNode;

import java.util.ArrayDeque;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _445_add_two_numbers_ii {
    /* 445. 两数相加 II
    给你两个 非空 链表来代表两个非负整数。
    数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
    将这两数相加会返回一个新的链表。

    你可以假设除了数字 0 之外，这两个数字都不会以零开头。

    进阶：

    如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     */

    // 反转解法 ，空间复杂度 O(1)
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode retNode = addTwoNumbersHelp(l1, l2);

        return reverse(retNode);
    }

    public ListNode addTwoNumbersHelp(ListNode l1, ListNode l2) {
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

    private ListNode reverse(ListNode head) {
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

    // 使用栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 将 l1 所有节点放到栈中
        ArrayDeque<ListNode> stack1 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        // 将 l2 所有节点放到栈中
        ArrayDeque<ListNode> stack2 = new ArrayDeque<>();
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        ListNode ans = null;
        // 用于存储两数相加时的进位
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;

            int sum = x + y + carry;

            ListNode curr = new ListNode(sum % 10);
            curr.next = ans;
            ans = curr;

            carry = sum / 10;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = ans;
            ans = curr;
        }

        return ans;
    }
}
