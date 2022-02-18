package com.douma._14_day_链表一._82;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _82_remove_duplicates_from_sorted_list_ii {
    /* 82. 删除排序链表中的重复元素 II
    存在一个按升序排列的链表，给你这个链表的头节点 head ，
    请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。

    返回同样按升序排列的结果链表

    输入：head = 1->2->3->3->4->4->5
    输出：1->2->5

    提示：
    链表中节点数目在范围 [0, 300] 内
    -100 <= Node.val <= 100
    题目数据保证链表已经按升序排列


     */

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;
        ListNode curr = head;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                do {
                    curr = curr.next;
                } while (curr.next != null && curr.val == curr.next.val);
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
