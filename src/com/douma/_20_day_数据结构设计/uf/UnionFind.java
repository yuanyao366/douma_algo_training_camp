package com.douma._20_day_数据结构设计.uf;

public interface UnionFind {

    // 将两个顶点连接
    void unionElement(int p, int q);

    // 判断两个顶点是否连接
    boolean isConnected(int p, int q);

    // 返回当前并查集中顶点的个数
    int size();
}
