class Solution {
public:
    // 分治
    int majorityElement1(vector<int>& nums) {
        return majorityElementHelp(nums, 0, nums.size() - 1);
    }

    int majorityElementHelp(vector<int>& nums, int lo, int hi) {
        if (lo == hi) return nums[lo];

        int mid = lo + (hi - lo) / 2;
        int leftNum = majorityElementHelp(nums, lo, mid);
        int rightNum = majorityElementHelp(nums, mid + 1, hi);

        if (leftNum == rightNum) return leftNum;
        int leftNumCnt = countInRange(nums, leftNum, lo, hi);
        int rightNumCnt = countInRange(nums, rightNum, lo, hi);
        return leftNumCnt > rightNumCnt ? leftNum : rightNum;
    }

    int countInRange(vector<int>& nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (num == nums[i]) count++;
        }
        return count;
    }

    // 摩尔投票算法
    int majorityElement(vector<int>& nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else if (count == 0) {
                count++;
                candidate = num;
            } else {
                count--;
            }
        }
        return candidate;
    }
};