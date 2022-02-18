class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        if (k <= 1) return 0;

        // 维护滑动窗口
        int left = 0, right = 0;
        // 存储乘积小于 k 的子数组的个数
        int ans = 0;
        // 当前窗口的所有元素的乘积
        int prod = 1;

        while (right < nums.size()) {
            // 更新当前窗口的累计乘积
            prod *= nums[right];
            // left 指针移动
            // 移动时机：当前窗口累计乘积大于等于 k
            // 移动策略：将累计乘积除以需要移除的左边的元素值
            while(prod >= k) prod /= nums[left++];

            /*
            [10,5,2,6]
            第一个窗口 [10]      --> 符合条件的子数组：1
            第二个窗口 [10, 5]   --> 符合条件的子数组：[10, 5] 和 [5] 即 2 个 = right - left + 1
            ......
             */
            ans += right - left + 1;

            // right 指针移动
            right++;
        }

        return ans;
    }
};