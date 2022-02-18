package com.douma.笔试代码.meituan._20210903;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _3_quadrangle {

    public static void main(String[] args) {
        // 1. 处理输入数据
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // 使用邻接表来表达输入图
        TreeSet<Integer>[] G = new TreeSet[n];
        for (int i = 0; i < n; i++) {
            G[i] = new TreeSet<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = scanner.nextInt();
                if (num == 1)  G[i].add(j);
            }
        }

        int res = 0;
        // 分别从每个顶点开始回溯走 4 步
        // 这里 n - 3 是一个优化：比如 n = 6 的话，那么就不用从顶点 3 开始选了
        // 因为从顶点 3 开始，后面只有 4 和 5 这两个顶点了，不可能组成四边形的
        for (int v = 0; v < n - 3; v++) {
            List<Integer> path = new ArrayList<>();
            path.add(v);
            res += dfs(G, 4, path);
        }

        System.out.println(res);
    }

    private static int dfs(TreeSet<Integer>[] G,
                           int targetPathLen, List<Integer> path) {
        int res = 0, last = path.get(path.size() - 1);

        // 只剩最后一步
        if (path.size() == targetPathLen - 1) {
            // 在上一步所到的顶点的相邻顶点中寻找是否可以到达起始点
            for (int v : G[last]) {
                if (v > path.get(1) &&  // 为了去重
                        !path.contains(v) &&
                        G[v].contains(path.get(0))) {
                    // 打印路径（目的是测试）
                    // System.out.println(path + " -> " + v);
                    res += 1;
                }
            }
            return res;
        }

        // 在上一步所到的顶点的相邻顶点中，选择下一步的顶点
        for (int v : G[last]) {
            if (v > path.get(0) && !path.contains(v)) {
                path.add(v);
                res += dfs(G, targetPathLen, path);
                // 回溯
                path.remove(path.size() - 1);
            }
        }

        return res;
    }
}
