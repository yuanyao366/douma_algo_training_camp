package com.douma.笔试代码.meituan._20210903;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_ab_string {

    // 3. 动态规划（状态数组压缩）
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(2);
            System.exit(0);
        }
        if (n == 2) {
            System.out.println(4);
            System.exit(0);
        }

        int prev = 2;
        int curr = 4;

        for (int i = 3; i <= n; i++) {
            int tmp = (prev + curr) % 998244353;
            prev = curr;
            curr = tmp;
        }

        System.out.println(curr);
    }

    // 2. 动态规划
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n == 1) {
            System.out.println(2);
            System.exit(0);
        }
        if (n == 2) {
            System.out.println(4);
            System.exit(0);
        }

        int[] dp = new int[n + 1];

        dp[1] = 2;
        dp[2] = 4;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 998244353;
        }

        System.out.println(dp[n]);
    }

    // 1. 记忆化搜索
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    private static int dfs(int n, int[] memo) {
        if (n == 1) return 2;
        if (n == 2) return 4;

        if (memo[n] != -1) return memo[n];

        memo[n] = (dfs(n - 1, memo) + dfs(n - 2, memo)) % 998244353;
        return memo[n];
    }



    private static int res = 0;
    private static void dfs(int n, int cnt, String abString) {
        if (cnt == n) {
            if (!abString.contains("aba") && !abString.contains("bab")) {
                res++;
            }
            return;
        }

        dfs(n, cnt + 1, abString + "a");
        dfs(n, cnt + 1, abString + "b");
    }
}
