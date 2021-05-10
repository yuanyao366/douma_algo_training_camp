class Solution {
public:
    int findKthPositive(vector<int>& arr, int k) {
        // 元素 a[i] 之前缺失的正整数的个数为：a[i] - i - 1
        if (arr[0] > k) return k;
        int left = 0;
        int right = arr.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            int x = mid < arr.size() ? arr[mid] : INT_MAX;
            if (x - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int leftMissCount = arr[left - 1] - (left - 1) - 1;
        return arr[left - 1] + (k - leftMissCount);
    }
};