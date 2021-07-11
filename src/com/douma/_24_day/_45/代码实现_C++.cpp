class Solution {
public:
    // 贪心策略：贪心策略：每步都选择能跳到的最远距离
    int jump(vector<int>& nums) {
        if (nums.size() == 1) return 0;
        int steps = 0;
        int start = 0, end = 0;
        while (end < nums.size() - 1) { // 走到最后一个位置的时候就不用走了
            int maxPos = 0;
            // 每次从 [start, end] 中都选择能跳到的最远距离
            for (int i = start; i <= end; i++) {
                maxPos = max(maxPos, i + nums[i]);
            }
            start = end + 1;
            end = maxPos;
            steps++;
        }
        return steps;
    }
};