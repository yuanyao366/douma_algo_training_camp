package com.douma._5_day_数学._1232;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1232_check_if_it_is_a_straight_line {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int deltaY = coordinates[1][1] - y0;
        int deltaX = coordinates[1][0] - x0;
        for (int i = 2; i < coordinates.length; i++) {
            int deltaYi = coordinates[i][1] - y0;
            int deltaXi = coordinates[i][0] - x0;

            if (deltaY * deltaXi != deltaYi * deltaX) return false;
        }
        return true;
    }
}
