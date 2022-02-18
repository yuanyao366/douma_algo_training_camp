package com.douma._20_day_数据结构设计._460;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key, val, count;
    Node next, prev;

    Node() {}

    Node(int key, int val, int count) {
        this.key = key;
        this.val = val;
        this.count = count;
    }
}

class DoubleLinkedList {
    private Node head;
    private Node tail;

    DoubleLinkedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    Node remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        return node;
    }

    // 将 node 拼接到表尾
    void add(Node node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    Node popFirst() {
        if (isEmpty()) return null;
        return remove(this.head.next);
    }

    boolean isEmpty() {
        return this.head.next == this.tail;
    }
}

class LFUCache2 {
    // 每个 key 对应的 Node 的映射
    private Map<Integer, Node> keyToNode;
    // 每个 count 对应的所有的 keys (按照最近使用的顺序组织)
    private Map<Integer, DoubleLinkedList> usedCountToKeys;

    private int capacity;

    // 记录整个缓存中所有 key 中使用最小的次数
    private int minUsedCount;

    public LFUCache2(int capacity) {
        keyToNode = new HashMap<>();
        usedCountToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        // 注意：capacity 可能为 0
        if (capacity == 0) return -1;

        Node node = keyToNode.get(key);
        if (node == null) return -1;

        // 维护这个 key 对应的 count
        int usedCount = node.count;
        // 1. 从这个 key 目前对应的 count 的集合中删除掉这个 key
        usedCountToKeys.get(usedCount).remove(node);
        node.count = usedCount + 1;

        // 2. 更新最小使用的次数
        // 如果当前的 usedCount 等于最小次数，
        // 并且当前的 usedCount 没有的 key，那么将最小次数加 1
        if (usedCount == minUsedCount
                && usedCountToKeys.get(usedCount).isEmpty()) {
            minUsedCount++;
        }

        // 3. 将 node 记录到 usedCount + 1 中的集合中
        putUsedCount(node, usedCount + 1);

        return node.val;
    }

    private void putUsedCount(Node node, int count) {
        if (!usedCountToKeys.containsKey(count)) {
            usedCountToKeys.put(count, new DoubleLinkedList());
        }
        usedCountToKeys.get(count).add(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            // 更新 key 对应的 value 值
            keyToNode.put(key, node);
            // 更新 key 对应的 count 值
            get(key);
            return;
        }

        if (keyToNode.size() == capacity) {
            // 删除最少使用的 key
            Node removeNode = usedCountToKeys.get(minUsedCount).popFirst();
            keyToNode.remove(removeNode.key);
        }

        // 新增一个缓存中不存在的 key
        Node node = new Node(key, value, 1);
        keyToNode.put(key, node);

        // 将 key 记录到 minUsedCount 中的集合中
        minUsedCount = 1;
        putUsedCount(node, minUsedCount);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
