package com.douma.笔试代码.jd._20211009;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _1_Stacker {
    // 稳固积木
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public static void main(String[] args) {
        // 1. 处理输入数据
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int k = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        // 2. 对积木重量升序排列
        // 排序的目的：可以快速的得到每个积木重量相差最小的积木
        Arrays.sort(weights);

        // 3. 找到所有相邻积木重量差大于 x 的间隔，并计算需要多少个任意重量积木可以填平这个间隔
        /*
        比如，n = 10, x = 2, k = 5
        积木重量：1, 1, 4, 9, 13, 15, 17, 19, 22, 29
        那么相邻积木重量差大于 x 的间隔是：1~4, 4~9, 9~13, 19~22, 22~29
        每个间隔需要的任意重量积木分别是：1, 2, 1, 1, 3
         */
        List<Integer> neededBricks = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int diff = weights[i] - weights[i - 1];
            if (diff > x) {
                // 要分 diff 是不是 x 的整数倍
                // 比如间隔 9 ~ 13 的 diff 值是 4， 是 x = 2 的 2 倍
                // 这个时候只需要 1 个任意木头，就可以填平了
                if ((double) diff / x > diff / x) {
                    neededBricks.add(diff / x);
                } else {
                    neededBricks.add(diff / x - 1);
                }
            }
        }

        /*
        假如我们得到的 needed_bricks = [1, 2, 1, 1, 3]
        也就是说，到现在为止积木堆的最小数量是 len(needed_bricks) + 1
        但是，我们还有 k 个任意重量的积木，可以用来填平上面的间隔
        那么，现在的问题就变成了：使用 k 个任意重量的积木去填平尽可能多的间隔
        说白了，就是要 needed_bricks 中元素值尽可能多的为 0
        这里可以使用贪心：
            局部最优是尽可能多的填平小间隔，从而可以达到全局的尽可能多的填平间隔
         */
        // 先排序
        Collections.sort(neededBricks);
        // 然后尽可能多的填平小间隔，并记录填平的间隔的数量 zeros
        int zeros = 0;
        for (int brick : neededBricks) {
            if (brick <= k) {
                zeros += 1;
                k -= brick;
            } else {
                break;
            }
        }

        // 最终的答案是：间隔的个数减去填平间隔的个数再 + 1
        System.out.println(neededBricks.size() - zeros + 1);
    }
}
