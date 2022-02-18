class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> path;
        vector<bool> used = vector<bool>(nums.size());
        dfs(nums, path, res, used);
        return res;
    }

    void dfs(vector<int> nums,
                vector<int>& path,
                vector<vector<int>>& res,
                vector<bool>& used) {
        if (path.size() == nums.size()) {
            res.emplace_back(path);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (used[i]) continue;
            path.emplace_back(nums[i]);
            used[i] = true;
            dfs(nums, path, res, used);
            path.pop_back();
            used[i] = false;
        }
    }
};