class Solution {
public:
    // 记忆化搜索（一）
    bool wordBreak2(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        unordered_map<int, bool> memo;
        return dfs(s, 0, dict, memo);
    }

    // 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    bool dfs(string s, int i, unordered_set<string>& wordDict, unordered_map<int, bool>& memo) {
        if (i == s.length()) return true;

        if (memo.count(i)) return memo[i];

        for (int end = i + 1; end <= s.length(); end++) {
            if (!wordDict.count(s.substr(i, end - i))) continue;

            if (dfs(s, end, wordDict, memo)) {
                memo[i] = true;
                return true;
            }
        }

        memo[i] = false;
        return memo[i];
    }

    // 动态规划（一）
    bool wordBreak3(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        // 1. 定义状态，dp[i] 表示以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
        vector<int> dp(s.length() + 1);

        // 2. 初始化
        // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[s.length()] = true;

        // 3. 状态转移方程
        // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (dp[j] && dict.count(s.substr(i, j - i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 4. 返回结果
        return dp[0];
    }

    // 记忆化搜索（二）
    bool wordBreak4(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        unordered_map<int, bool> memo;
        return dfs2(s, s.length(), dict, memo);
    }

    // 以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    bool dfs2(string s, int i, unordered_set<string>& wordDict, unordered_map<int, bool>& memo) {
        if (i == 0) return true;

        if (memo.count(i)) return memo[i];

        for (int start = i - 1; start >= 0; start--) {
            if (!wordDict.count(s.substr(start, i - start))) continue;

            if (dfs2(s, start, wordDict, memo)) {
                memo[i] = true;
                return true;
            }
        }

        memo[i] = false;
        return memo[i];
    }

    // 动态规划（二）
    bool wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        // 1. 定义状态，dp[i] 表示以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
        vector<int> dp(s.length() + 1);

        // 2. 初始化
        // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[0] = true;

        // 3. 状态转移方程
        // 如果 dp[j] == true 且 s[j, i) 存在于字典中，那么 dp[i] = true
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.count(s.substr(j, i - j))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 4. 返回结果
        return dp[s.length()];
    }
};