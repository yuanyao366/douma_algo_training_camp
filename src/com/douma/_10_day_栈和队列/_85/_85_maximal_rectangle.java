package com.douma._10_day_栈和队列._85;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _85_maximal_rectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        int[][] left = new int[m][n];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1') {
                    left[row][col] = (col == 0 ? 0 : left[row][col - 1]) + 1;
                }
            }
        }

        int ans = 0;
        for (int col = 0; col < n; col++) {
            int[] up = new int[m];
            int[] down = new int[m];
            Arrays.fill(down, m);

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int row = 0; row < m; row++) {
                while (!stack.isEmpty() && left[row][col] <= left[stack.peek()][col]) {
                    down[stack.peek()] = row;
                    stack.pop();
                }
                up[row] = (stack.isEmpty() ? -1 : stack.peek());
                stack.push(row);
            }

            for (int row = 0; row < m; row++) {
                int height = left[row][col];
                int width = down[row] - up[row] - 1;
                ans = Math.max(ans, height * width);
            }
        }

        return ans;
    }
}
