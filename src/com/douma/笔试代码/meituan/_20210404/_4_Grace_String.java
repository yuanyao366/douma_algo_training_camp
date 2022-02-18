package com.douma.笔试代码.meituan._20210404;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
// issue 讨论链接：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CNN1
public class _4_Grace_String {
    private static List<List<Character>> allSubsets;

    // 时间复杂度：O(n!)
    // 空间复杂度：O(n!)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        // 1. 使用回溯，拿到字符串 str 的所有的子序列 (或者说子集)
        char[] chars = str.toCharArray();
        allSubsets = new ArrayList<>();
        dfs(chars, 0, new ArrayList<>());

        // 2. 将有重复字符的子序列过滤掉，并返回没有重复字符的子序列的数量
        int cnt = filter();

        System.out.println(cnt);
    }

    private static void dfs(char[] chars,
                            int startIndex,
                            List<Character> subset) {
        allSubsets.add(new ArrayList<>(subset));

        for (int i = startIndex; i < chars.length; i++) {
            subset.add(chars[i]);
            dfs(chars, i + 1, subset);
            subset.remove(subset.size() - 1);
        }
    }

    private static int filter() {
        int res = 0;
        for (List<Character> subset : allSubsets) {
            if (hasSameChar(subset)) continue;
            res++;
        }
        return res;
    }

    private static boolean hasSameChar(List<Character> subset) {
        // 因为字符都是小写字母，所以可以用一个长度为 26 的数字来判重
        boolean[] exists = new boolean[26];
        for (char c : subset) {
            if (exists[c - 'a']) {
                return true;
            }
            exists[c - 'a'] = true;
        }
        return false;
    }
}
