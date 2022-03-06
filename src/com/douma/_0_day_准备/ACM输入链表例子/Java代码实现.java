package com.douma._0_day_准备.ACM输入链表例子;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class Java代码实现 {
    /*
    ACM 模式下，如果输入的是链表，
    需要自己根据输入数据，在内存中构建链表
    比如有道题目输入是链表。然后会给出下面一行数据：
    23 22 1 5 6 3
     */
    // 1. 首先需要定义一个节点类
    private static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // 2. 然后构建一个单链表
        Scanner scanner = new Scanner(System.in);
        Node dummyNode = new Node(-1);
        Node curr = dummyNode;
        while (scanner.hasNext()) {
            curr.next = new Node(scanner.nextInt());
            curr = curr.next;
        }
        // 对 dummyNode 操作

        // 最后打印的时候，是要遍历操作后的链表
    }
}
