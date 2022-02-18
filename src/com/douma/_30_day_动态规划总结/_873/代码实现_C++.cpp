class Solution {
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        int n = arr.size();

        unordered_map<int, int> indexes;
        for (int i = 0; i < n; i++) {
            indexes[arr[i]] = i;
        }

        int ans = 0;
        // dp[i][j]：表示以 arr[i]，arr[j] 为结尾的最长的斐波那契子序列的长度
        vector<vector<int>> dp(n, vector<int>(n));
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                int arrk = arr[j] - arr[i];
                // 在 [0...i] 中找到一个元素 arr[k]，使得 arr[k] + arr[i] = arr[j]
                // 所以需要保证 arrk < arr[i]
                if (indexes.count(arrk) && arrk < arr[i]) {
                    int k = indexes[arrk];
                    dp[i][j] = max(dp[i][j], dp[k][i] + 1);
                    ans = max(ans, dp[i][j] + 2);
                }
            }
        }
        if (ans < 3) return 0;
        return ans;
    }
};