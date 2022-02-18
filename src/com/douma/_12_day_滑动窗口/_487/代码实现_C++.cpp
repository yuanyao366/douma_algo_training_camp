class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int ans = 0;
        int left = 0, right = 0;
        int zeroIndex = -1;
        while (right < nums.size()) {
            if (nums[right] == 0) {
                if (zeroIndex >= 0) {
                    ans = max(ans, right - left);
                    left = zeroIndex + 1;
                }
                zeroIndex = right;
            }
            right++;
        }
        return max(ans, right - left);
    }
};