class Solution {
public:
    // 动态规划
    int wiggleMaxLength1(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) {
            return n;
        }
        vector<int> up(n);
        vector<int> down(n);
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return max(up[n - 1], down[n - 1]);
    }

    // 动态规划 + 状态压缩
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        if (n < 2) {
            return n;
        }
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return max(up, down);
    }
};