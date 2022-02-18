package com.douma._25_day_贪心算法二._861;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _861_score_after_flipping_matrix {
    /* 861. 翻转矩阵后的得分
    有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

    移动是指选择任一行或列，
    并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

    在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

    返回尽可能高的分数。

    示例：
    输入：[[0,0,1,1],
          [1,0,1,0],
          [1,1,0,0]]
     [[1,1,1,1],  --> 15
      [1,0,0,1],  --> 9
      [1,1,1,1]]  --> 15
    输出：39
    解释：
    转换为 [[1,1,1,1], --> 2^3  1 = 4 - 0 - 1
           [1,0,0,1],
           [1,1,1,1]] --> 2^1 = 2
    0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     
    提示：
    1 <= A.length <= 20
    1 <= A[0].length <= 20
    A[i][j] 是 0 或 1
     */

    public int matrixScore(int[][] A) {
        int rows = A.length, cols = A[0].length;
        // 使得每一行都从 1 开头
        for (int row = 0; row < rows; row++) {
            if (A[row][0] == 0) {
                // 行翻转
                for (int col = 0; col < cols; col++) {
                    A[row][col] ^= 1;
                }
            }
        }

        int res = 0;
        // 1 的数量越多，得到的数值越大
        for (int col = 0; col < cols; col++) {
            int cnt = 0;
            // 统计第 col 列有多少个 1。
            for (int row = 0; row < rows; row++) {
                cnt += A[row][col];
            }

            int maxOneCnt = Math.max(cnt, rows - cnt);
            res += maxOneCnt * (1 << (cols - col - 1));
        }

        return res;
    }
}
