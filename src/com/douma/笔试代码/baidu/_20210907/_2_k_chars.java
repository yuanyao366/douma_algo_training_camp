package com.douma.笔试代码.baidu._20210907;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_k_chars {
    private static int res = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();

        // 1. 计算所有小写字母出现的次数
        // 并拿到出现的小写字母
        int[] cnt = new int[26];
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
            set.add(c);
        }

        // 2. 从出现的字母中挑选 k 个字母，并计算这 k 个字母的组合数
        // 累加所有的组合数就是结果
        List<Character> chars = new ArrayList<>(set);
        dfs(cnt, k, chars, 0, new ArrayList<>());

        System.out.println(res);
    }

    // 回溯从 chars 中挑选 k 个字母
    // 并根据每个字母出现的次数，计算这 k 个字母对应组合数
    // 时间复杂度：O((n！/(k！* (n-k)!) * k)，其中 n = 26
    private static void dfs(int[] cnt, int k, List<Character> chars,
                            int index, List<Character> path) {
        if (k == path.size()) {
            // 计算 k 个字母对应组合数
            /*
            比如：字母 abc
            然后 a 出现的次数是 3，b 出现的次数是 2，c 出现的次数是 1
            那么排列的组合数是：
                (2^3 - 1) * (2^2 - 1) * (2^1 - 1) = 7 * 3 * 1 = 21
             */
            long tmp = 1L;
            for (char c : path) {
                tmp *= ((long)Math.pow(2, cnt[c - 'a']) - 1) % ((long)Math.pow(10, 9) + 7);
            }
            res += (int)(tmp % ((long)Math.pow(10, 9) + 7));
            return;
        }

        for (int i = index; i < chars.size(); i++) {
            path.add(chars.get(i));
            dfs(cnt, k, chars,  i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
