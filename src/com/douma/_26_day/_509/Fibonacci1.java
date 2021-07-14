package com.douma._26_day._509;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class Fibonacci1 {
    // DFS
    // 时间复杂度：O(2^n)
    // 因为 n 最大为 30，比较小，所以不会超时
    // 空间复杂度：O(logn) 递归系统栈需要的空间开销
    public int fib(int n) {
        return dfs(n);
    }
    // 时间复杂度：O(2^n)
    private int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);

        return leftFib + rightFib;
    }
}
