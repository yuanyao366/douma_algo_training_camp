class Solution {
public:
    vector<vector<int>> combine(int n, int k) {
        vector<vector<int>> res;
        if (n <= 0 || k <= 0 || k > n) return res;
        vector<int> combination;
        dfs(n, k, 1, combination, res);
        return res;
    }

    void dfs(int n, int k, int start,
                vector<int>& combination,
                vector<vector<int>>& res) {
        if (combination.size() == k) {
            res.emplace_back(combination);
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.push_back(i);
            dfs(n, k, i + 1, combination, res);
            combination.pop_back();
        }
    }
};