class Solution {
public:
    int minSwaps(vector<int>& data) {
        // 1. 统计数组中元素值等于 1 的个数
        int k = 0;
        for (int x : data) {
            if (x == 1) k++;
        }

        // 维护窗口大小为 k 的滑动窗口
        int left = 0, right = 0;

        // 所有窗口中最少的 0 的数量
        int minZeroCnt = INT_MAX;
        // 存储每个窗口中 0 的数量
        int windowZeroCnt = 0;
        while (right < data.size()) {
            if (data[right] == 0) windowZeroCnt++;
            if (right - left + 1 == k) {
                minZeroCnt = min(minZeroCnt, windowZeroCnt);
                if (data[left] == 0) windowZeroCnt--;
                left++;
            }
            right++;
        }
        return minZeroCnt == INT_MAX ? 0 : minZeroCnt;
    }
};