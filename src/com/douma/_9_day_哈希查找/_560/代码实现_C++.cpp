class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> mp;
        mp[0] = 1;

        int res = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            prefixSum += nums[i];
            int diff = prefixSum - k;
            if (mp.find(diff) != mp.end()) {
                res += mp[diff];
            }
            mp[prefixSum]++;
        }

        return res;
    }
};