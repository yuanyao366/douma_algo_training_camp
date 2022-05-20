package com.douma.笔试代码.huawei._20220420;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _1_新员工考试 {

    // 3 种题型以及它对应的题目数和每题分数
    static int[][] questions = {{10, 2}, {10, 4}, {5, 8}};

    static List<List<Integer>> comb;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int targetScore = scanner.nextInt();

        if (targetScore == 100) {
            System.out.println(1);
            return;
        }

        comb = new ArrayList<>();

        int res = 0;
        List[] list = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            list[i] = new ArrayList();
        }

        for (int i = 0; i < 25; i++) {

        }

        System.out.println(comb);
    }

    private static void dfs(int index, int sum,
                            int errorScore,
                            List<Integer> path) {
        // 两种情况则退出：
        // 1. 错的题目数量超过了 3 道题
        // 2. 错的题目数目等于 3，但是最后错的一题是判断题或者单选题
        //      这个时候，即使 sum == errorScore，那么得到的总分也不可能等于 targetScore
        //      因为考试马上终止了，剩下的题一分得不到
        // 比如 你错了 3 道判断题，那么你得分不会超过 20 - (2 * 3) = 14
        if (path.size() > 3) return;

        if (sum == errorScore) {
            comb.add(new ArrayList<>(path));
        }

        for (int i = index; i < 3; i++) {
            path.add(i);
            dfs(i, sum + questions[i][1], errorScore, path);
            path.remove(path.size() - 1);
        }
    }
}
