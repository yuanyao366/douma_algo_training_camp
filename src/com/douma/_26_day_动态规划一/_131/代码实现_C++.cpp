class Solution {
private:
    vector<vector<string>> res;
    string s;
public:
    // 方法二： 使用动态规划优化判断是否是回文串
    vector<vector<string>> partition(string s) {
        this->s = s;
        int len = s.length();

        // 定义状态，dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        vector<vector<bool>> dp(len, vector<bool>(len));
        // 状态初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 状态转移
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                bool isCharEqual = s[i] == s[j];
                if (j - i == 1) { // 只有两个字符
                    dp[i][j] = isCharEqual;
                } else {
                    dp[i][j] = isCharEqual && dp[i + 1][j - 1];
                }
            }
        }

        vector<string> path;
        dfs(0, path, dp);
        return res;
    }

    void dfs(int startIndex, vector<string>& path, vector<vector<bool>> dp) {
        if (startIndex == s.length()) {
            res.emplace_back(path);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (!dp[startIndex][i]) continue;
            path.push_back(s.substr(startIndex, i - startIndex + 1));
            dfs(i + 1, path);
            path.pop_back();
        }
    }

    // 方法一
    vector<vector<string>> partition1(string s) {
        this->s = s;
        vector<string> path;
        dfs(0, path);
        return res;
    }

    void dfs(int startIndex, vector<string>& path) {
        if (startIndex == s.length()) {
            res.emplace_back(path);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (!checkPalindrome(s, startIndex, i)) continue;
            path.push_back(s.substr(startIndex, i - startIndex + 1));
            dfs(i + 1, path);
            path.pop_back();
        }
    }

    bool checkPalindrome(string str, int left, int right) {
        while (left < right) {
            if (str[left] != str[right]) return false;
            left++;
            right--;
        }
        return true;
    }
};