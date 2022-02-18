package com.douma._12_day_滑动窗口.practice._1234;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1234_replace_the_substring_for_balanced_string {
    /* leetcode 1234. 替换子串得到平衡字符串
        有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
        假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
        给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
        你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
        请返回待替换子串的最小可能长度。
        如果原字符串自身就是一个平衡字符串，则返回 0。

        示例一：
        输入：s = "QWER"
        输出：0
        解释：s 已经是平衡的了。

        示例二：
        输入：s = "QQWE"
        输出：1
        解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。

        示例三：
        输入：s = "QQQW"
        输出：2
        解释：我们可以把前面的 "QQ" 替换成 "ER"。

        示例四：
        输入：s = "QQQQ"
        输出：3
        解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。

        提示：
        1 <= s.length <= 10^5
        s.length 是 4 的倍数
        s 中只含有 'Q', 'W', 'E', 'R' 四种字符
     */

    public int balancedString(String s) {
        // count 的作用：
        // 1. 首先用于统计整个字符串中所有字符的个数
        // 2. 然后用于维护滑动窗口外的字符的个数
        int[] count = new int[128];
        char[] arr = s.toCharArray();

        for (char c : arr) {
            count[c]++;
        }

        int need = s.length() / 4;
        int ans = arr.length;

        // 滑动窗口
        int left = 0, right = 0;
        while (right <= arr.length) {
            // 如果 count 中的字符的个数有一个大于 need 的话，则移动 right
            // 扩大窗口，减去 count 中相应字符出现的次数
            if (count['Q'] > need || count['W'] > need
                    || count['E'] > need || count['R'] > need) {
                if (right >= arr.length) break;
                char rightCh = arr[right];
                // 滑动窗口内的字符的个数减一
                count[rightCh]--;
                right++;
                continue;
            }

            // 缩小窗口，加上 count 中相应字符出现的次数
            // 这个时候 count 中的字符的个数都 小于等于 need
            // 开始移动 left，并且将 left 对应的字符的个数加一
            ans = Math.min(ans, right - left);
            if (ans == 0) break;

            char leftCh = arr[left];
            count[leftCh]++;
            left++;
        }

        return ans;
    }
}
