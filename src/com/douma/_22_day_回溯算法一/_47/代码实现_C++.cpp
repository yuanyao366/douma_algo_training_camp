class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        // 排序，去重的基础
        sort(nums.begin(), nums.end());
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
            // 去重的条件
            // 对于 !used[i - 1] 的解释请见 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I48M6Q
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            path.push_back(nums[i]);
            used[i] = true;
            dfs(nums, path, res, used);
            path.pop_back();
            used[i] = false;
        }
    }
};