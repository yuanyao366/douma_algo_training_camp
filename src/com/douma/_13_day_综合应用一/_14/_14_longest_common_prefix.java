package com.douma._13_day_综合应用一._14;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _14_longest_common_prefix {
    /* 14. 最长公共前缀
    编写一个函数来查找字符串数组中的最长公共前缀。

    如果不存在公共前缀，返回空字符串 ""。

    示例 1：
    输入：strs = ["flower","flow","flight"]
    输出："fl"

    示例 2：
    输入：strs = ["dog","racecar","car"]
    输出：""

    提示：
        0 <= strs.length <= 200
        0 <= strs[i].length <= 200
        strs[i] 仅由小写英文字母组成

     */

    // 1. 纵向扫描
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";

        int rows = strs.length;
        int cols = strs[0].length();
        for (int i = 0; i < cols; i++) {
            char firstChar = strs[0].charAt(i);
            for (int j = 1; j < rows; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != firstChar) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // 2. 横向扫描
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(1)
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) return "";
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    // 3. 分治思想
    // 时间复杂度：O(mn) m 表示字符串数组中所有字符串的平均长度，n 表示字符串数组的大小
    // 空间复杂度：O(mlogn) 空间复杂度主要取决于递归调用的层数，层数最大为 logn，每层需要 m 的空间存储返回结果。
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) return "";
        return lcp(strs, 0, strs.length - 1);
    }

    private String lcp(String[] strs, int left, int right) {
        if (left == right) return strs[left];

        int mid = left + (right - left) / 2;
        String leftLcp = lcp(strs, left, mid);
        String rightLcp = lcp(strs, mid + 1, right);

        return longestCommonPrefix(leftLcp, rightLcp);
    }
}
