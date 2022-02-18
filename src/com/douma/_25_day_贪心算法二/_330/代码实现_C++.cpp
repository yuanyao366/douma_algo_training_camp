class Solution {
public:
    int minPatches(vector<int>& nums, int n) {
        int res = 0;
        long x = 1;
        int index = 0;
        while (x <= n) {
            if (index < nums.size() && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                res++;
            }
        }
        return res;
    }
};