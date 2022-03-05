class Solution {
public:
    // 完全背包问题：
    // 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s
    /*
    物品：单词数组
    背包重量：字符串
    背包价值：单词是否组合成功字符串

    dp[i][c],表示前 i 个单词，是否可以组成长度为 c 的字符串，
    由于每个字符串都需所有的单词组合一遍，因此将背包重量的循环放到物品个数的循环之前。

    每个物品都有放入和不放入的情况
    当物品不放入背包
        dp[i][c]=dp[i - 1][c]
    当物品放入背包
        dp[i][c]=dp[i][c - w.size()]
     */
    // 压缩物品维度
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