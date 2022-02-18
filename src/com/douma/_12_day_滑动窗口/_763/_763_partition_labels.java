package com.douma._12_day_滑动窗口._763;

import java.util.ArrayList;
import java.util.List;

public class _763_partition_labels {
    /* leetcode 763. 划分字母区间
   字符串 S 由小写字母组成。
   我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
   返回一个表示每个字符串片段的长度的列表。

    示例：
    输入：S = "ababcbacadefegdehijhklij"
    输出：[9,7,8]
    解释：
    划分结果为 "ababcbaca", "defegde", "hijhklij"。
    每个字母最多出现在一个片段中。
    像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     

    提示：
    S的长度在[1, 500]之间。
    S只包含小写字母 'a' 到 'z'
     */

    public List<Integer> partitionLabels(String S) {
        // 计算并存储每个字符在数组中所在的最大索引
        int[] c2Index = new int[26];
        for (int i = 0; i < S.length(); i++) {
            c2Index[S.charAt(i) - 'a'] = i;
        }

        // 存储最终结果
        List<Integer> res = new ArrayList<>();

        // 维护窗口
        int left = 0;
        int right = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            right = Math.max(right, c2Index[c - 'a']);

            if (i == right) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        return res;
    }
}
