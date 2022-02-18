package com.douma._19_day_DFS_BFS._733;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _733_flood_fill {
    /* 733. 图像渲染
    `有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

    给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，
    让你重新上色这幅图像。

    为了完成上色工作，从初始坐标开始，
    记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
    接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，
    ……
    重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

    最后返回经过上色渲染后的图像。`
     */

    private int[][] image;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows;
    private int cols;

    private int oldColor;
    // DFS
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        this.image = image;
        this.rows = image.length;
        this.cols = image[0].length;

        dfs(sr, sc, newColor);
        return image;
    }

    private void dfs(int row, int col, int newColor) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return;
        }

        image[row][col] = newColor;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol, newColor);
        }
    }

    // BFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }

        int rows = image.length;
        int cols = image[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            for (int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && image[nextRow][nextCol] == oldColor) {
                    queue.offer(new int[]{nextRow, nextCol});
                    image[nextRow][nextCol] = newColor;
                }
            }
        }

        return image;
    }
}
