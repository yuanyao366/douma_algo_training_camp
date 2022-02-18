package com.douma.笔试代码.meituan._20210903;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 菲菲
 */
public class _4_by_bus {

    // 因为 n 和 m 的最大值是 500，而且 n + m 最大也为 500
    // 所以下面的时间复杂度是可以接受的
    // 时间复杂度：O(n(n + m))
    // 空间复杂度：O(n^2)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 记录每一条公交线上的公交站，用于后面的建图
        List<Integer>[] busStations = new List[m + 1];
        for (int i = 0; i <= m; i++) {
            busStations[i] = new ArrayList<>();
        }
        // 时间复杂度：O(nm)
        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                busStations[scanner.nextInt()].add(i);
            }
        }

        // 根据每一条公交线上的公交站，建图
        List<Integer>[] graph = new List[n];
        for (int v = 0; v < n; v++) {
            graph[v] = new ArrayList<>();
        }
        // 时间复杂度：O(n^2)
        for (List<Integer> adj : busStations) {
            // 每条公交线上的公交站都是相互直接连接的
            for (int v : adj) {
                for (int w : adj) {
                    if (v != w) graph[v].add(w);
                }
            }
        }

        int[][] res = new int[n][n];
        // 依次从每个公交站开始，进行 BFS ，可以计算这个公交站到每一个其他公交站的最短路径
        // 时间复杂度：O(n^2)
        // 空间复杂度：O(n^2)
        for (int v = 0; v < n; v++) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(v);
            boolean[] visited = new boolean[n];
            visited[v] = true;
            int shortestPath = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int next = queue.poll();
                    res[v][next] = shortestPath;
                    for (int w : graph[next]) {
                        if (!visited[w]) {
                            queue.offer(w);
                            visited[w] = true;
                        }
                    }
                }
                shortestPath++;
            }
        }

        // 打印结果
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
                if (j < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
