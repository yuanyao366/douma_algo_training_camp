class Solution {
public:
    int longestOnes(vector<int>& nums, int k) {
        int ans = 0;
        int left = 0, right = 0;
        // 记录当前窗口中 1 的个数
        int oneCnt = 0;
        while (right < nums.size()) {
            if (nums[right] == 1) oneCnt++;
            while ((right - left + 1) - oneCnt > k) {
                if (nums[left] == 1) oneCnt--;
                left++;
            }
            ans = max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
};