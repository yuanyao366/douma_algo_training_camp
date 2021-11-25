package com.douma;

/**
 * 抖码算法，让算法学习变的简单有趣
 *  公众号：抖码课堂
 * @作者 : 老汤
 */
public class Test1 {
    public static void main(String[] args) {
        move(3, 1, 2, 3);
    }
    // 将 n 个盘子从 1 移动到 3, 2 作为缓冲
    public static void move(int n, int one, int two, int three) {
        if (n == 1) {
            System.out.println(String.format("将 1 个盘子从 %d 移动到 %d", one, three));
            return;
        }
        // 将 n - 1 个盘子从 1 移动到 2, 3 作为缓冲
        move(n - 1, one, three, two);
        // 将第一个盘子从 1 移动到 3
        move(1, one, two, three);
        // 将 n - 1 个盘子从 2 移动到 3, 1 作为缓冲
        move(n - 1, two, one, three);
    }
}
