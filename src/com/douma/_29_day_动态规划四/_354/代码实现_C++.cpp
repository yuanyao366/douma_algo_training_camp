class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        sort(envelopes.begin(), envelopes.end(), [](const auto& o1, const auto& o2) {
            return o1[0] < o2[0] || (o1[0] == o2[0] && o1[1] > o2[1]);
        });

        int n = envelopes.size();
        vector<int> dp(n, 1);
        int ans = 1;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (envelopes[j][1] > envelopes[i][1]) {
                    dp[j] = max(dp[i] + 1, dp[j]);
                    ans = max(ans, dp[j]);
                }
            }
        }

        return ans;
    }
};