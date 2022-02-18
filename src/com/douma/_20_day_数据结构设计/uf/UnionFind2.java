package com.douma._20_day_数据结构设计.uf;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class UnionFind2 implements UnionFind{
    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;

    public UnionFind2(int capacity) {
        parent = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h) h 表示树的深度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        parent[pRoot] = qRoot;
    }

    @Override
    public int size() {
        return parent.length;
    }
}
