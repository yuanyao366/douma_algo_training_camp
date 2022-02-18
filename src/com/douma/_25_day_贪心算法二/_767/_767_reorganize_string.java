package com.douma._25_day_贪心算法二._767;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _767_reorganize_string {
    /* 767. 重构字符串
    给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

    若可行，输出任意可行的结果。若不可行，返回空字符串。

    示例 1:
    输入: S = "aab"
    输出: "aba"

    示例 2:
    输入: S = "aaab"
    输出: ""

    注意:
    S 只包含小写字母并且长度在[1, 500]区间内。

     */

    public String reorganizeString(String s) {
        // 1. 统计每个字符出现的次数
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] count = new int[26];
        for (char c : chars) {
            int index = c - 'a';
            count[index]++;
            if (count[index] > (n + 1) / 2) return "";
        }

        // 2. 拿到出现次数最多的字符
        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > count[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        // 3. 先将出现次数最多的字符放在偶数索引上
        char[] res = new char[n];
        int idx = 0;
        while (count[maxCountIndex] > 0) {
            res[idx] = (char) (maxCountIndex + 'a');
            idx += 2;
            count[maxCountIndex]--;
        }

        // 4. 将其他的字符放到其他的位置
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                if (idx >= n) idx = 1;
                res[idx] = (char) (i + 'a');
                idx += 2;
                count[i]--;
            }
        }

        return new String(res);
    }
}
