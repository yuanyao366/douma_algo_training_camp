package com.douma._4_day._6;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _6_zigzag_conversion {
    // 按行访问
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        int delta = 2 * numRows - 2;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col + row < s.length(); col += delta) {
                sb.append(s.charAt(col + row));
                if (row != 0 && row != numRows - 1 && col + delta - row < s.length()) {
                    sb.append(s.charAt(col + delta - row));
                }
            }
        }
        return sb.toString();
    }
    public String convert2(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];

        int currRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            sbs[currRow].append(c);
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;
            currRow += (goingDown ? 1 : -1);
        }

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            res.append(sbs[j]);
        }

        return res.toString();
    }

    public String convert1(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];

        int n = s.length();
        int i = 0;
        while (i < n) {
            // 垂直向下
            for (int idx = 0; idx < numRows && i < n; idx++) {
                sbs[idx].append(s.charAt(i));
                i++;
            }

            // 右前向上
            for (int idx = numRows - 2; idx >= 1 && i < n; idx++) {
                sbs[idx].append(s.charAt(i));
                i++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < numRows; j++) {
            res.append(sbs[j]);
        }

        return res.toString();
    }
}
