class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int ans = INT_MAX;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.size()) {
            windowSum += nums[right];
            while (windowSum >= target) {
                ans = min(ans, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return ans == INT_MAX ? 0 : ans;
    }
};