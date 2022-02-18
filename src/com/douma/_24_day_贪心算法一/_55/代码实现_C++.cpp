class Solution {
public:
    // 贪心策略：每步都选择能跳到的最远距离
    bool canJump(vector<int>& nums) {
        int maxPos = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i > maxPos) return false;
            maxPos = max(maxPos, i + nums[i]);
        }
        return true;
    }
};