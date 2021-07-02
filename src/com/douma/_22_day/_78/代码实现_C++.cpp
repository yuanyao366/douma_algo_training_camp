class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
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
            subset.push_back(nums[i]);
            findSubsets(nums, i + 1, subset, res);
            subset.pop_back();
        }
    }
};