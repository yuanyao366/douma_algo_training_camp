class Solution {
public:
    // 回溯
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> dict(wordDict.begin(), wordDict.end());
        return dfs(s, 0, dict);
    }

    vector<string> dfs(string s, int i, unordered_set<string>& dict) {
        vector<string> res;
        if (i == s.length()) {
            res.push_back("");
            return res;
        }

        for (int end = i + 1; end <= s.length(); end++) {
            string word = s.substr(i, end - i);
            if (!dict.count(word)) continue;
            vector<string> list = dfs(s, end, dict);
            for (string str : list) {
                string split = str == "" ? "" : " ";
                res.push_back(word + split + str);
            }
        }

        return res;
    }
};