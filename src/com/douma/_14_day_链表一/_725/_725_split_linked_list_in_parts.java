package com.douma._14_day_链表一._725;

import com.douma._14_day_链表一.ListNode;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _725_split_linked_list_in_parts {
    /* 725. 分隔链表
        给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
    每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。

    这 k 个部分应该按照在链表中出现的顺序进行输出，
    并且排在前面的部分的长度应该大于或等于后面的长度。

    返回一个符合上述规则的链表的列表。

    输入：root = 1->2->3->4->5->6->7->8->9->10      k = 3
    输出：[
        1->2->3->4,
        5->6->7,
        8->9->10
    ]

    输入：root = 1->2->3         k = 5
    输出：[
        1,
        2,
        3,
        null,
        null
    ]

    提示：
    root 的长度范围： [0, 1000].
    输入的每个节点的大小范围：[0, 999].
    k 的取值范围： [1, 50].
     */

    public ListNode[] splitListToParts1(ListNode root, int k) {
        int len = 0;
        ListNode curr = root;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int width = len / k;
        int remainder = len % k; // 余数

        ListNode[] res = new ListNode[k];
        curr = root;
        for (int i = 0; i < k; i++) {
            ListNode dummyNode = new ListNode(-1);
            ListNode node = dummyNode;
            int realWidth = width + (i < remainder ? 1 : 0);
            for (int j = 0; j < realWidth; j++) {
                node.next = new ListNode(curr.val);
                node = node.next;
                if (curr != null) curr = curr.next;
            }
            res[i] = dummyNode.next;
        }
        return res;
    }


    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode curr = root;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int width = len / k;
        int remainder = len % k; // 余数

        ListNode[] res = new ListNode[k];
        curr = root;
        for (int i = 0; i < k; i++) {
            ListNode head = curr;
            // 这里 -1 的原因：每一段 curr 需要走的步数比这一段的节点数少 1 个
            // 比如链表：1 -> 2 -> 3 -> 4
            // 链表的长度为 4 ，也就是 4 个节点
            // 但是从第一个节点开始，只需要走 3 步就可以到达最后一个节点
            int realWidth = width + (i < remainder ? 1 : 0) - 1;
            for (int j = 0; j < realWidth; j++) {
                if (curr != null) curr = curr.next;
            }
            // bug 修复：判断 curr 不等于 null
            if (curr != null) {
                ListNode next = curr.next;
                curr.next = null;
                curr = next;
            }
            res[i] = head;
        }
        return res;
    }
}
