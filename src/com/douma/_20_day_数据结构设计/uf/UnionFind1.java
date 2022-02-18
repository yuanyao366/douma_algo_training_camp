package com.douma._20_day_数据结构设计.uf;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class UnionFind1 implements UnionFind{
    // id[i] 表示的是顶点 i 所属的集合
    private int[] id;

    public UnionFind1(int capacity) {
        id = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            id[i] = i;
        }
    }

    // 查找元素 p 所属的集合
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        // 数组的顺序访问的话，JVM 会优化，CPU
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) id[i] = pId;
        }
    }

    @Override
    public int size() {
        return id.length;
    }
}
