package com.douma._15_day_链表二._138;

import com.douma._15_day_链表二.ListNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _138_copy_list_with_random_pointer {
    /* 138. 复制带随机指针的链表
    给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，
    该指针可以指向链表中的任何节点或空节点。

    构造这个链表的深拷贝。 
    深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
    新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，
    并使原链表和复制链表中的这些指针能够表示相同的链表状态。
    复制链表中的指针都不应指向原链表中的节点 。

    例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
    那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。

    返回复制链表的头节点。

    用一个由 n 个节点组成的链表来表示输入/输出中的链表。
    每个节点用一个 [val, random_index] 表示：
        val：一个表示 Node.val 的整数。
        random_index：随机指针指向的节点索引（范围从 0 到 n-1）；
        如果不指向任何节点，则为  null 。

    你的代码 只 接受原链表的头节点 head 作为传入参数。

    0 <= n <= 1000
    -10000 <= Node.val <= 10000
    Node.random 为空（null）或指向链表中的节点。
     */

    // 拷贝单向链表，迭代解法
    public ListNode copyLinkedList0(ListNode head) {
        if (head == null) return null;

        ListNode oldNode = head;
        ListNode newNode = new ListNode(oldNode.val);
        ListNode newHead = newNode;
        while (oldNode != null && oldNode.next != null) {
            newNode.next = new ListNode(oldNode.next.val);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return newHead;
    }

    // 拷贝单向链表，递归解法
    public ListNode copyLinkedList(ListNode head) {
        if (head == null) return null;

        ListNode newNode = new ListNode(head.val);
        ListNode p = copyLinkedList(head.next);
        newNode.next = p;

        return newNode;
    }

    // 拷贝单向链表，显示栈
    public ListNode copyLinkedList1(ListNode head) {
        if (head == null) return null;

        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode newHead = null;
        while (!stack.isEmpty()) {
            ListNode curr = stack.pop();
            ListNode newNode = new ListNode(curr.val);
            // bug 修复：这里是 newNode
            newNode.next = newHead;
            newHead = newNode;
        }

        return newHead;
    }

    private Map<Node, Node> map = new HashMap<>();
    // 1. 递归解法
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node newNode = new Node(head.val);
        map.put(head, newNode);

        newNode.next = copyRandomList(head.next);
        newNode.random = map.get(head.random);

        return newNode;
    }

    // 2. 迭代解法
    public Node copyRandomList1(Node head) {
        if (head == null) return null;

        Node oldNode = head;
        Node newNode = new Node(oldNode.val);

        // bug 修复
        map.put(oldNode, newNode);

        Node newHead = newNode;
        while (oldNode != null) {
            newNode.next = getCloneNode(oldNode.next);
            newNode.random = getCloneNode(oldNode.random);

            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return newHead;
    }

    private Node getCloneNode(Node node) {
        if (node == null) return null;
        if (!map.containsKey(node)) {
            map.put(node, new Node(node.val));
        }
        return map.get(node);
    }

    // 3. 用新旧节点交替的方式，模拟 map 的功能
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        // 1. 新旧节点交替
        // 创建新节点，并且放在旧节点的后面
        // 假设原先链表是这样：A->B->C，那么创建新节点后，链表变成：A->A'->B->B'->C->C'
        // 其中 A' 是 A 的克隆节点
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        // 2. 设置正确的 random
        curr = head;
        while (curr != null) {
            curr.next.random = (curr.random != null) ? curr.random.next : null;
            curr = curr.next.next;
        }

        // 3. 分割新旧链表
        // 将 A->A'->B->B'->C->C' 切割成：A->B->C 和 A'->B'->C'
        Node currOld = head;
        Node currNew = head.next;
        Node newHead = head.next;
        while (currOld != null) {
            currOld.next = currOld.next.next;
            currNew.next = (currNew.next != null) ? currNew.next.next : null;

            currOld = currOld.next;
            currNew = currNew.next;
        }
        return newHead;
    }
}
