class Solution {
public:
    // 滑动窗口
    double findMaxAverage(vector<int>& nums, int k) {
        int maxSum = INT_MIN;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.size()) {
            windowSum += nums[right];
            if (right - left + 1 == k) {
                maxSum = max(maxSum, windowSum);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return (double)maxSum / k;
    }
};