class Solution {
public:
    int findLengthOfLCIS(vector<int>& nums) {
        int ans = 1;
        int slow = 0;
        for (int fast = 1; fast < nums.size(); fast++) {
            if (nums[fast - 1] >= nums[fast]) {
                slow = fast;
            }
            ans = max(ans, fast - slow + 1);
        }
        return ans;
    }
};