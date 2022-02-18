package com.douma.笔试代码.huawei.rongyao._20210906;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_TextHandler {

    private static int L = 0;
    private static String[] words;

    // 时间复杂度：O(n)，n 表示字符串的长度
    // 空间复杂度：O(n)
    public static void main(String[] args) {
        // 1. 处理输入数据
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        words = s.split(",");
        L = scanner.nextInt();

        List<String> res = new ArrayList<>();
        // 2. 使用滑动窗口处理文本字符串
        int left = 0, right = 0;
        int windowLen = 0;
        while (right < words.length) {
            // 这里加 1 的原因是：每个单词之间至少会有 1 个 *
            if (right > left) windowLen += 1;
            windowLen += words[right].length();

            // 2.1. 处理最后一个窗口
            if (right == words.length - 1) {
                res.add(processLastWindow(left, right));
                right++;
            } else if (windowLen > L) {
                // 2.2. 处理非最后一个窗口
                // 因为要处理的窗口的长度 <= L，所以这里是 right - 1
                res.add(processNonLastWindow(left, right - 1));

                // 处理完当前窗口，将处理下一个窗口，并清零 windowLen
                left = right;
                windowLen = 0;
            } else {
                right++;
            }
        }

        for (String line : res) {
            System.out.println(line);
        }
    }

    private static String processLastWindow(int left, int right) {
        StringBuilder line = new StringBuilder();
        int charsLen = 0;
        // 1. 单词与单词之间仅需一个 *
        for (int i = left; i <= right; i++) {
            line.append(words[i]);
            charsLen += words[i].length();
            if (charsLen < L) {
                line.append("*");
                charsLen++;
            }
        }
        // 2. 剩余位置用 * 补足
        while (charsLen < L) {
            line.append("*");
            charsLen++;
        }
        return line.toString();
    }

    private static String processNonLastWindow(int left, int right) {
        StringBuilder line = new StringBuilder();
        // 1. 第一个单词与左边对齐
        int charsLen = words[left].length();
        line.append(words[left]);

        // 2. 如果只有一个单词的话，那么剩下的位置用 * 补足
        if (right - left + 1 == 1) {
            while (charsLen < L) {
                line.append("*");
                charsLen++;
            }
        } else {
            // 3. 有两个或者两个以上的单词
            // 3.1 先计算中间每个单词之间的长度
            int[] gaps = getWordGaps(left, right);
            // 3.2 根据每个间隔之间的长度填充字符串
            for (int i = 0; i < gaps.length; i++) {
                // 先填充 *
                for (int j = 0; j < gaps[i]; j++) {
                    line.append("*");
                }
                // 再填充单词
                left++;
                line.append(words[left]);
            }
        }
        return line.toString();
    }

    private static int[] getWordGaps(int left, int right) {
        // 1. 先计算除了单词的长度，剩余的长度大小
        int remain = L;
        for (int i = left; i <= right; i++) {
            remain -= words[i].length();
        }
        // 2. 计算间隔的数量
        int gapCnt = right - left;
        // 3. 计算每个间隔的长度
        // 单词间的 * 应均匀分布，无法均匀分布的话，则左边可以比右边多一个 *
        // 比如间隔是 3， 剩余长度是 8 的话，那么间隔大小为：[3, 3, 2]
        int[] gaps = new int[gapCnt];
        int a = remain / gapCnt, b = remain % gapCnt;
        for (int i = 0; i < gapCnt; i++) {
            gaps[i] = a;
            if (i < b) gaps[i] += 1;
        }
        return gaps;
    }

}
