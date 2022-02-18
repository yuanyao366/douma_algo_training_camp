package com.douma._31_day_图论_正在更新._785;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _785_is_graph_bipartite {

    private boolean[] visited;
    // -1 表示没有染颜色
    // 0 红色 1 蓝色
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;


        this.visited = new boolean[graph.length];
        this.colors = new int[graph.length];
        Arrays.fill(colors, -1);
        // 遍历图中每个顶点
        for (int v = 0; v < graph.length; v++) {
            // 先判断，没有遍历的顶点才能进行深度优先遍历
            if (!visited[v]) {
                if (!bfs(v)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 如果 v 的颜色是 1，那么 w 的颜色就是 0
                // 如果 v 的颜色是 0，那么 w 的颜色就是 1
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v]) {
                // 如果相邻顶点的颜色一样的话，则不是二分图
                return false;
            }
        }
        return true;
    }

    private boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int w : graph[curr]) {
                // 如果 w 没有遍历过，则需要染色
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 给顶点 w 染色，和 curr 的颜色不一样
                    colors[w] = 1 - colors[curr];
                } else if (colors[w] == colors[curr]) {
                    // 如果 w 被访问过，并且它的颜色和相邻点一样
                    // 那么可以判定不是二分图
                    return false;
                }
            }
        }
        return true;
    }
}
