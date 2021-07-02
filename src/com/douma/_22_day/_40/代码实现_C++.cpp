class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        vector<vector<int>> res;
        if (candidates.size() == 0) return res;
        vector<int> combination;
        findCombinationSum(candidates, 0, target, combination, res);
        return res;
    }

    void findCombinationSum(vector<int>& candidates,
                     int startIndex,
                     int target,
                     vector<int>& combination,
                     vector<vector<int>>& res) {
        if (target < 0) return;
        if (target == 0) {
            res.emplace_back(combination);
            return;
        }

        for (int i = startIndex; i < candidates.size(); i++) {
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;
            combination.push_back(candidates[i]);
            findCombinationSum(candidates, i + 1, target - candidates[i], combination, res);
            // 回溯的过程中，将当前的节点从 combination 中删除
            combination.pop_back();
        }
    }
};