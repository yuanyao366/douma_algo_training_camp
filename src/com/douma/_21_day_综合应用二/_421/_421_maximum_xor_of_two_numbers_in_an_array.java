package com.douma._21_day_综合应用二._421;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _421_maximum_xor_of_two_numbers_in_an_array {
    /*  421. 数组中两个数的最大异或值
    给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，
    其中 0 ≤ i ≤ j < n 。

    进阶：你可以在 O(n) 的时间解决这个问题吗？

    示例 1：
    输入：nums = [3,10,5,25,2,8]
    输出：28
    解释：最大运算结果是 5 XOR 25 = 28.

    提示：
    1 <= nums.length <= 2 * 10^4
    0 <= nums[i] <= 2^31 - 1
     */

    // 暴力解法
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int findMaximumXOR1(int[] nums) {
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                res = Math.max(res, nums[i] ^ nums[j]);
            }
        }
        return res;
    }

    // 二进制前缀树
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            trie.add(nums[i - 1]);
            res = Math.max(res, trie.maxXor(nums[i]));
        }
        return res;
    }
}

class Trie {
    private class Node {
        Node zero;
        Node one;

        Node() {}
    }

    private Node root;
    public Trie() {
        root = new Node();
    }

    // 将 num 的二进制从高位到低位添加到前缀树中
    public void add(int num) {
        Node curr = root;
        // 整数的最高一位是符号位，对于正数的话是 0
        //      0100 0000 0000 0000 0000 0000 0000 0000 >> 30
        // ->   0000 0000 0000 0000 0000 0000 0000 0001
        // & 1  0000 0000 0000 0000 0000 0000 0000 0001
        // bug 修复，是 k--
        for (int k = 30; k >= 0 ; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (curr.zero == null) curr.zero = new Node();
                curr = curr.zero;
            } else {
                if (curr.one == null) curr.one = new Node();
                curr = curr.one;
            }
        }
    }

    // 在前缀树中查找和 num 进行异或，得到的最大的异或值
    public int maxXor(int num) {
        Node curr = root;
        int x = 0;
        // bug 修复，是 k--
        for (int k = 30; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (curr.one != null) {
                    curr = curr.one;
                    x = 2 * x + 1;
                } else {
                    curr = curr.zero;
                    x = 2 * x;
                }
            } else {
                if (curr.zero != null) {
                    curr = curr.zero;
                    x = 2 * x + 1;
                } else {
                    curr = curr.one;
                    x = 2 * x;
                }
            }
        }
        return x;
    }
}
