class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.size()) {
            if (nums[right] == 0) {
                ans = max(ans, right - left);
                left = right + 1;
            }
            right++;
        }
        return max(ans, right - left);
    }
};