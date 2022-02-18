package com.douma._2_day_一维数组._665;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _665_non_decreasing_array {
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                cnt++;
                if (cnt > 1) return false;

                if (i - 2 >= 0 && nums[i] < nums[i - 2]) {
                    nums[i] = nums[i - 1];
                } else { // 注意：实际上这个 else 分支是可以去掉的
                    /*
                    解释这里为什么是这样修改：
                    如果 nums[i] >= nums[i - 2] 时候，修改 nums[i] = nums[i - 1] 的话，会导致：修改 nums[i] 后，会影响 i 及其后面的顺序

                    如果只是修改 nums[i - 1] = nums[i] 的话，则不会影响 i 及其后面的顺序

                    如果 nums[i] < nums[i - 2] 时候，没办法，只能修改 nums[i] = nums[i - 1]，虽然也会影响 i 及其后面的顺序，但这是不可免的

                    因为我们的算法是需要保证语义：[0...i] 是非递减的

                    而且我们还要求尽可能少的修改次数，这样我们就需要尽可能少的影响 i 及其后面的顺序
                    所以我们只能这样来选择策略了：如果 nums[i] < nums[i - 2] 时候修改 nums[i] = nums[i - 1]；如果 nums[i] >= nums[i - 2] 时候修改 nums[i - 1] = nums[i]

                     比如：[-1,4,2,3]

                    如果 i 指向的是 2，这个时候，nums[i] > nums[i - 2] 的，如果修改 nums[i] = nums[i - 1]，那么数组变为 [-1, 4, 4, 3]

                    这样导致 i 及其后面的顺序变了，需要再次修改，才可以使得数组非递增

                    如果这个时候修改 nums[i - 1] = nums[i] 的话，那么数组变为 [-1, 2, 2, 3]，这样数组只需一次修改，就变为非递减数列了

                    这里其实还需要注意一个边界条件，那就是 i < 2 的时候，这个时候也是修改 nums[i - 1] = nums[i] ，目的也是为了尽可能少的影响 i 及其后面的顺序
                     */
                    nums[i - 1] = nums[i];
                }
            }
        }
        return true;
    }
}
