class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        // 维护滑动窗口
        int left = 0, right = 0;
        // 最大长度
        int ans = 0;
        // 当前窗口的预算
        int windowCost = 0;
        while (right < s.size()) {
            // 更新当前窗口的预算
            windowCost += abs(s[right] - t[right]);

            // left 指针移动
            // 移动时机：当前窗口的预算大于最大预算的时候
            // 移动策略：减去 left 指针需要的预算
            while (windowCost > maxCost) {
                windowCost -= abs(s[left] - t[left]);
                left++;
            }

            // 到这里，当前窗口的预算不会大于最大预算
            // 计算最大长度
            ans = max(ans, right - left + 1);

            // 右指针移动
            right++;
        }
        return ans;
    }
};