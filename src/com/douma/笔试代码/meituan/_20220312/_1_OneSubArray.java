package com.douma.笔试代码.meituan._20220312;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_OneSubArray {

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // dp1[i] 表示以 nums[i] 结尾的乘积等于 1 的子数组个数
        int[] dp1 = new int[n];
        // dp2[i] 表示以 nums[i] 结尾的乘积等于 -1 的子数组个数
        int[] dp2 = new int[n];

        if (nums[0] == 1) {
            dp1[0] = 1;
        } else {
            dp2[0] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] == 1) {
                // 因为 1 × 1 = 1，所以：
                // 以 nums[i] 结尾的乘积等于 1 的子数组个数 等于：
                // 以 nums[i - 1] 结尾的乘积等于 1 的子数组个数，再加上 1
                // 因为这个时候多了 1 个子数组，就是 [nums[i]] 本身
                dp1[i] = dp1[i - 1] + 1;
                // 因为 1 × -1 = -1，所以以 nums[i] 结尾的乘积等于 -1 的子数组个数
                // 还是等于以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数
                dp2[i] = dp2[i - 1];
            } else {
                // 因为 -1 × -1 = 1，所以：
                // 以 nums[i] 结尾的乘积等于 1 的子数组个数 等于：
                // 以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数
                dp1[i] = dp2[i - 1];
                // 因为 -1 × 1 = -1，所以：
                // 以 nums[i] 结尾的乘积等于 -1 的子数组个数 等于：
                // 以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数，再加上 1
                // 因为这个时候多了 1 个子数组，就是 [nums[i]] 本身
                dp2[i] = dp1[i - 1] + 1;
            }
        }

        // 最终将每一个以 nums[i] 结尾的乘积等于 1 的子数组个数累加即得到结果
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dp1[i];
        }

        System.out.println(ans);
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // dp[i] 表示 nums 的前 i 个元素中 -1 的个数
        int[] dp = new int[n + 1];
        dp[0] = 0;

        int minusOnes = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1) minusOnes++;
            dp[i + 1] = minusOnes;
        }

        int ans = 0;

        // 枚举数组的所有区间
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 区间 [j...i] 中 -1 的个数等于：
                // 前 i 个元素中 -1 的个数减去前 j - 1 个元素中 -1 的个数
                // 如果区间的 -1 的个数是偶数的话，那么这个区间的所有元素相乘结果肯定是 1
                if ((dp[i] - dp[j - 1]) % 2 == 0) ans++;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // 前缀积
        // prefixProduct[i] 表示数组 nums 前 i 个元素，i 从 0 开始
        int[] prefixProduct = new int[n + 1];
        // 前 0 个元素表示没有元素，因为求乘积，所以赋值为 1
        prefixProduct[0] = 1;
        for (int i = 0; i < n; i++) {
            prefixProduct[i + 1] = prefixProduct[i] * nums[i];
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 求区间 [j...i] 的乘积
                int tmp = prefixProduct[i] / prefixProduct[j - 1];
                if (tmp == 1) ans++;
            }
        }

        System.out.println(ans);
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int tmp = 1;
                // 求区间 [j...i] 的乘积
                for (int k = j; k <= i; k++) {
                    tmp *= nums[k];
                }
                if (tmp == 1) ans++;
            }
        }

        System.out.println(ans);
    }
}
