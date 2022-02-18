package com.douma._14_day_链表一._142;

import com.douma._14_day_链表一.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _142_linked_list_cycle_ii {
    /* 142. 环形链表 II
    给定一个链表，返回链表开始入环的第一个节点。
    如果链表无环，则返回 null。
     */
    // 哈希查找
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) return head;
            visited.add(head);
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }

}
