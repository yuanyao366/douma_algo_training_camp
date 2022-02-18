package com.douma._12_day_滑动窗口.practice._187;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _187_repeated_dna_sequences {
    /* leetcode 187. 重复的DNA序列
       所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
       在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

    编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。

    示例 1：
    输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
    输出：["AAAAACCCCC","CCCCCAAAAA"]

    示例 2：
    输入：s = "AAAAAAAAAAAAA"
    输出：["AAAAAAAAAA"]
     

    提示：
    0 <= s.length <= 105
    s[i] 为 'A'、'C'、'G' 或 'T'

     */

    // 暴力解法
    // 时间复杂度：O(10n)
    public List<String> findRepeatedDnaSequences1(String s) {
        int n = s.length();
        if (n <= 10) return new ArrayList<>();
        int k = 10;

        Set<String> seen = new HashSet<>();
        Set<String> output = new HashSet<>();

        for (int i = 0; i < n - k + 1; i++) {
            String str = s.substring(i, i + k);
            if (seen.contains(str)) output.add(str);
            else seen.add(str);
        }

        return new ArrayList<>(output);
    }

    // 参考【课程 A 中应用篇】的字符串匹配 RK 算法，使用滚动哈希方法
    // 时间复杂度：O(n)
    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        if (n <= 10) return new ArrayList<>();
        int k = 10;

        // 将 ACGT 看成 4 进制
        Map<Character, Integer> toInt = new HashMap<>();
        toInt.put('A', 0);
        toInt.put('C', 1);
        toInt.put('G', 2);
        toInt.put('T', 3);
        int base = 4;
        int shiftLeft = (int)Math.pow(base, k - 1);

        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();

        int currWindowHash = 0;
        // 计算第一个窗口对应的 hash 值
        for (int i = 0; i < k; ++i) {
            currWindowHash = currWindowHash * base + toInt.get(s.charAt(i));
        }
        seen.add(currWindowHash);

        int left = 1, right = k; // 从第二个窗口开始
        while (right < n) {
            // 删除 s.charAt(left - 1)
            currWindowHash = currWindowHash - toInt.get(s.charAt(left - 1)) * shiftLeft;
            // 添加 s.charAt(right)
            currWindowHash = currWindowHash * base + toInt.get(s.charAt(right));
            if (seen.contains(currWindowHash)) output.add(s.substring(left, right + 1));
            else seen.add(currWindowHash);

            left++;
            right++;
        }

        return new ArrayList<>(output);
    }
}
