class Solution {
public:
    int reversePairs(vector<int>& nums) {
        vector<int> tmp(nums.size());
        return mergeSort(nums, 0, (int)nums.size() - 1, tmp);
    }

    int mergeSort(vector<int>& nums, int lo, int hi, vector<int>& tmp) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int left = mergeSort(nums, lo, mid, tmp);
        int right = mergeSort(nums, mid + 1, hi, tmp);

        int mergeCount = merge(nums, lo, mid, hi, tmp);
        return left + right + mergeCount;
    }

    int merge(vector<int>& nums, int lo, int mid, int hi, vector<int>& tmp) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        int count = 0;

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) nums[k] = tmp[j++];
            else if (j == hi + 1) nums[k] = tmp[i++];
            else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                count += (mid - i + 1);
            }
        }
        return count;
    }
};