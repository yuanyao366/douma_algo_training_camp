package com.douma._23_day_回溯算法二._842;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _842_split_array_into_fibonacci_sequence {
    /* 842. 将数组拆分成斐波那契序列
        给定一个数字字符串 S，比如 S = "123456579"，
        我们可以将它分成斐波那契式的序列 [123, 456, 579]。

    形式上，斐波那契式序列是一个非负整数列表 F，且满足：
    0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
    F.length >= 3；
    对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
    另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
    "0123" -> "0", "23"

    返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。

    示例 1：
    输入："12345657"
    输出：[123,456,579]

    示例 2：
    输入: "11235813"
    输出: [1,1,2,3,5,8,13]

    示例 3：
    输入: "112358130"
    输出: []
    解释: 这项任务无法完成。

    示例 4：
    输入："0123"
    输出：[]
    解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。

    示例 5：
    输入: "1101111"
    输出: [110, 1, 111]
    解释: 输出 [11,0,11,11] 也同样被接受。
     
    提示：
    1 <= S.length <= 200
    字符串 S 中只含有数字。

     */
    private String num;
    private List<Integer> res;

    public List<Integer> splitIntoFibonacci(String num) {
        this.num = num;
        this.res = new ArrayList<>();
        dfs(0, 0, 0);
        return res;
    }

    private boolean dfs(int startIndex, int prevTwoNumSum, int prevNum) {
        if (startIndex == num.length()) {
            return res.size() >= 3;
        }

        long currLongNum = 0;
        for (int i = startIndex; i < num.length(); i++) {
            // 如果当前字符是 '0' 的话，则不做处理，因为数字不能以 0 开头
            if (i > startIndex && num.charAt(startIndex) == '0') continue;
            currLongNum = currLongNum * 10 + (num.charAt(i) - '0');
            if (currLongNum > Integer.MAX_VALUE) break;
            int currNum = (int) currLongNum;

            if (res.size() >= 2) {
                if (currNum < prevTwoNumSum) {
                    continue;
                } else if (currNum > prevTwoNumSum) {
                    break;
                }
            }

            res.add(currNum);
            if (dfs(i + 1, currNum + prevNum, currNum)) return true;
            res.remove(res.size() - 1);
        }

        return false;
    }
}
