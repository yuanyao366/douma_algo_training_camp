class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        if (n <= 0) return res;
        dfs(n, "", res, 0, 0);
        return res;
    }

    void dfs(int n, string path, vector<string>& res, int open, int close) {
        if (open > n || close > open) return;

        if (path.length() == 2 * n) {
            res.push_back(path);
            return;
        }

        dfs(n, path + "(", res, open + 1, close);
        dfs(n, path + ")", res, open, close + 1);
    }
};