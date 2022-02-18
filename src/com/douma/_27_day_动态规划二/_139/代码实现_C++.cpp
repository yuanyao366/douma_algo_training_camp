class Solution {
public:
    // 完全背包问题：
    // 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s
    bool wordBreak1(string s, vector<string>& wordDict) {
        // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
        vector<bool> dp(s.size() + 1);
        dp[0] = true;

        // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
        for (int i = 1; i <= s.length(); i++) {
            for (string word : wordDict) {
                int wordLen = word.length();
                if (i - wordLen >= 0 && s.substr(i - wordLen, wordLen) == word) {
                    dp[i] = dp[i] || dp[i - wordLen];
                }
            }
        }

        return dp[s.length()];
    }
};