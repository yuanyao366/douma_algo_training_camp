package com.douma._20_day_数据结构设计._200;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _200_number_of_islands {
    /* 200. 岛屿数量
        给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

    岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

    此外，你可以假设该网格的四条边均被水包围。

    示例 1：

    输入：grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    输出： 1

    示例 2：

    输入：grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    输出： 3
     */

    // 并查集
    public int numIslands(char[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        if (nextRow >= 0 && nextRow < rows
                                && nextCol >= 0 && nextCol < cols
                                && grid[nextRow][nextCol] == '1') {
                            uf.unionElement(i * cols + j, nextRow * cols + nextCol);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }
}

class UnionFind {
    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;
    // rank[i] 表示以 i 为根的集合所表示的树的深度。
    private int[] rank;

    private int count = 0;

    public UnionFind(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        parent = new int[rows * cols];
        rank = new int[rows * cols];
        // bug 修复：i 应该是小于 rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    parent[i * cols + j] = i * cols + j;
                    rank[i * cols + j] = 1;
                    count++;
                }
            }
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h) h 表示树的深度
    // O(h)  --> O(1) < O(log*n) < O(logn)
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

        count--;
    }

    public int getCount() {
        return count;
    }
}
