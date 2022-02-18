package com.douma._24_day_贪心算法一._402;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _402_RemoveKdigits {
    // 贪心策略：每次删除前面比较大的数字
    // O(k*n)
    public String removeKdigits(String num, int k) {
        StringBuilder numStr = new StringBuilder(num);
        for (int i = 0; i < k; i++) {
            boolean hasDeleted = false;
            for (int j = 1; j < numStr.length(); j++) {
                // 如果前面的数字大，则删除
                if (numStr.charAt(j) < numStr.charAt(j - 1)) {
                    numStr.deleteCharAt(j - 1);
                    hasDeleted = true;
                    break;
                }
            }
            // 说明序列是递增的，那么删除最后一个字符
            if (!hasDeleted) numStr.deleteCharAt(numStr.length() - 1);
        }
        // 删除前面是 0 的字符
        int len = numStr.length();
        while (len != 0) {
            if (numStr.charAt(0) > '0') break;
            numStr.deleteCharAt(0);
            len = numStr.length();
        }

        return numStr.length() == 0 ? "0" : numStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _402_RemoveKdigits().removeKdigits("10200", 1));
    }
}
