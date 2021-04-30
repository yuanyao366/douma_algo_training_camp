package com.douma._5_day._204;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _204_count_primes {
    // O(nlog(logn))
    public int countPrimes(int n) {
        int ans = 0;
        boolean[] notPrimes = new boolean[n];
        for (int x = 2; x < n; x++) {
            if (notPrimes[x]) continue;
            ans++;
            // 如果 x 是质数，那么 2x、3x、4x.... 肯定不是质数
            for (int i = x; i < n; i += x) {
                notPrimes[i] = true;
            }
        }
        return ans;
    }
    // O(n^2)
    public int countPrimes1(int n) {
        int ans = 0;
        for (int x = 2; x < n; x++) {
            ans += isPrime(x) ? 1 : 0;
        }
        return ans;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
