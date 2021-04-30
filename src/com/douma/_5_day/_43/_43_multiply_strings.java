package com.douma._5_day._43;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _43_multiply_strings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + x * y;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder currAns = new StringBuilder();
            // 对当前位的后面补 0
            for (int j = n - 1; j > i ; j--) {
                currAns.append("0");
            }
            int y = num2.charAt(i) - '0';
            int carry = 0;
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + carry;
                currAns.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) currAns.append(carry);
            // 字符串相加
            addStrings(ans, currAns.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 < 0 ? 0 : num1.charAt(l1) - '0';
            int y = l2 < 0 ? 0 : num2.charAt(l2) - '0';

            int sum = x + y + carry;
            res.append(sum % 10);
            carry = sum / 10;

            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }
}
