package com.douma._14_day_链表一._237;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _237_delete_node_in_a_linked_list {
    /* 237. 删除链表中的节点

        请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。
        传入函数的唯一参数为 要被删除的节点 。

        输入：4 -> 5 -> 1 -> 9 -> null
            node = 5
        输出：4 -> 1 -> 9 -> null

        提示：
            链表至少包含两个节点。
            链表中所有节点的值都是唯一的。
            给定的节点为非末尾节点并且一定是链表中的一个有效节点。
            不要从你的函数中返回任何结果。
     */

    // 时间复杂度：O(n)
    public void deleteNode1(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            if (next != null) {
                node.val = next.val;
            } else {
                prev.next = null;
            }
            prev = node;
            node = node.next;
        }
    }

    // 代码优化
    // 时间复杂度：O(1)
    public void deleteNode(ListNode node) {
        // 实际上，我们只需要将下一个节点的值覆盖掉要删除的节点的值就可以
        node.val = node.next.val;
        // 然后将下一个节点从链表中断开
        ListNode next = node.next;
        node.next = next.next;
        next.next = null;
    }
}
