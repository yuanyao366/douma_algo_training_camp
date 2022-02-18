package com.douma._3_day_二维数组._118;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _118_pascals_triangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            List<Integer> oneRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    oneRow.add(1);
                } else {
                    List<Integer> preRow = rows.get(row - 1);
                    oneRow.add(preRow.get(col - 1) + preRow.get(col));
                }
            }
            rows.add(oneRow);
        }
        return rows;
    }
}
