package com.douma._2_day_一维数组._189;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _189_rotate_array {
    // 方案三：数组旋转
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
    // 方案二：环状替换
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        //另一种方式： 这个循环的次数是可以计算得到的，见下面的代码
        for (int start = 0; count < n; start++) {
            int curr = start;
            int prev = nums[start];
            // 循环替换
            do {
                int next = (curr + k) % n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;

                curr = next;
                count++;
            } while (start != curr);
        }
    }

    // 方案二：环状替换
    public void rotate22(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 计算循环的次数等于 n 和 k 的公约数，那么为什么可以这么计算呢？
        /*
            我们先来看一个例子：
                nums = [1, 2, 3, 4, 5, 6, 7, 8, 9]
                k = 6
            从索引 0 开始，每次走 k 步，遍历元素顺序：0 -> 6 -> 3 -> 0 (走了 3 圈，遍历了 4 个元素)
            从索引 1 开始，每次走 k 步，遍历元素顺序：1 -> 4 -> 7 -> 1 (走了 3 圈，遍历了 4 个元素)
            从索引 2 开始，每次走 k 步，遍历元素顺序：2 -> 8 -> 5 -> 2 (走了 3 圈，遍历了 4 个元素)

            从索引为 0 的位置开始，每次走 k 步，最终会回到索引为 0 的位置
            这个中间可能走了好几圈数组，假设走了 a 圈，又假设这个过程总共遍历的 b 个元素

            那么 a * n = b * k 的，那么 b = (a * n) / k，也就是说一次遍历，可以遍历到的元素个数是 (a * n) / k
            那么现在有 n 个元素，需要遍历的次数是：n / b = n / ((a * n) / k) = (n * k) / (a * n)
            这里只有 a 的值是未知的，那么 a 的值是多大呢？

            首先 a 肯定是 n 的倍数（因为 a 是好几圈的数组长度），所以 a * n 的话也肯定是 n 的倍数
            又因为 a * n = b * k，所以 a * n 又是 k 的倍数
            也就是说 a * n 是 n 和 k 的公倍数，

            又因为每次遍历的时候，在第一次回到起点时就结束，因此 a 的值要尽可能的小
            所以 a * n 是 n 和 k 的【最小】公倍数，记为：a * n = lcm(n, k)

            所以遍历的次数 count = n / b = n / ((a * n) / k) = (n * k) / (a * n) = (n * k) / lcm(n, k)
            也就是 n * k 除以 n 和 k 的最小公倍数，结果就是 n 和 k 的最大公约数
            比如 n = 9, k = 6，那么 lcm(9, 6) = 18，那么 (9 * 6) / lcm(9, 6) = 3 ，即遍历次数为 3，也就是上面例子的遍历次数
            再比如 n = 5, k = 3，那么 lcm(5, 3) = 15，那么 (5 * 3) / lcm(5, 3) = 1 ，即遍历次数为 1，即一次性就可以循环遍历所有元素
            比如：nums = [1, 2, 3, 4, 5]  k = 3
            遍历的顺序：0 -> 2 -> 5 -> 3 -> 0，一遍就可以遍历完
         */
        int count = gcd(k, n);
        for (int start = 0; start < count; start++) {
            int curr = start;
            int prev = nums[start];
            // 循环替换
            do {
                int next = (curr + k) % n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;

                curr = next;
            } while (start != curr);
        }
    }
    // 计算 x 和 y 的最大公约数
    // 比如 6 和 2 的最大公约数是 2。 10 和 4 的最大公约数是 2
    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public static void main(String[] args) {
        System.out.println(new _189_rotate_array().gcd(15, 4));
    }

    // 方案一：使用额外数组
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] newArr = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int index = (i + k) % n;
            newArr[index] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
}
