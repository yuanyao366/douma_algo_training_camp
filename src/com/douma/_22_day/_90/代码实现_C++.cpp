class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        vector<int> subset;
        findSubsets(nums, 0, subset, res);
        return res;
    }

    void findSubsets(vector<int>& nums,
                     int startIndex,
                     vector<int>& subset,
                     vector<vector<int>>& res) {
        res.emplace_back(subset);

        for (int i = startIndex; i < nums.size(); i++) {
            // i > startIndex 的目的就是为了排除 i == startIndex 的情况，也就是保证 i 不是第一个子节点
            // 因为第一个子节点前面没有节点的，那么 nums[i] == nums[i - 1] 就没有意义的
            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            subset.push_back(nums[i]);
            findSubsets(nums, i + 1, subset, res);
            subset.pop_back();
        }
    }
};