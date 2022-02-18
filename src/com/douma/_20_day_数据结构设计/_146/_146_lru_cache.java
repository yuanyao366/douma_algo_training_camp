package com.douma._20_day_数据结构设计._146;

import java.util.HashMap;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _146_lru_cache {
}

class LRUCache {
    private class Node {
        Integer key;
        Integer value;
        Node next;
        Node prev;
    }
    private Map<Integer, Node> cache;
    private int capacity;

    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;

        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // 将查询到的 node 移动到表头
        moveNodeToHead(node);
        return node.value;
    }

    private void moveNodeToHead(Node node) {
        // 1. 删除节点 node
        removeNode(node);

        // 2. 将节点 node 添加到表头
        addNodeToHead(node);
    }

    private void removeNode(Node node) {
        Node preNode = node.prev;
        Node nextNode = node.next;

        preNode.next = nextNode;
        nextNode.prev = preNode;

        node.prev = null;
        node.next = null;
    }

    // 将一个节点添加到头节点
    private void addNodeToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;

        head.next = node;
        node.prev = head;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // 1. 判断缓存容量大小
            if (cache.size() == capacity) {
                Node delNode = removeTailNode();
                cache.remove(delNode.key);
            }
            // 2. 创建节点
            node = new Node();
            node.key = key;
            node.value = value;

            // 3. 维护链表和缓存
            cache.put(key, node);
            addNodeToHead(node);
        } else {
            node.value = value;
            // 有的话，则将节点放到头结点
            moveNodeToHead(node);
        }
    }

    private Node removeTailNode() {
        Node delNode = tail.prev;
        removeNode(delNode);
        return delNode;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
