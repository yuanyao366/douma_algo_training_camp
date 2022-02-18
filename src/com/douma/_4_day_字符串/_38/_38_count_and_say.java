package com.douma._4_day_字符串._38;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _38_count_and_say {
    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;

        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();

            char say = prev.charAt(0);
            int count = 1;
            for (int j = 1; j < prev.length(); j++) {
                if (prev.charAt(j) == say) {
                    count++;
                } else {
                    curr.append(count).append(say);
                    say = prev.charAt(j);
                    count = 1;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }
}
