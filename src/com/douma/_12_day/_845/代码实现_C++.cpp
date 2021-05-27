class Solution {
public:
    int longestMountain(vector<int>& arr) {
        int n = arr.size();
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[right]) {
                while (right + 1 < n && arr[right] < arr[right + 1]) right++;
                if (right + 1 < n && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1]) right++;
                    ans = max(ans, right - left + 1);
                } else {
                    right++;
                }
            }
            left = right;
        }
        return ans;
    }
};