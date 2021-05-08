class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        vector<long> prefixSum(nums.size() + 1);
        prefixSum[0] = 0;
        for (int i = 0; i < nums.size(); i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        vector<long> tmp(nums.size() + 1);
        return mergeSort(prefixSum, lower, upper, 0, prefixSum.size() - 1, tmp);
    }

    int mergeSort(vector<long>& prefixSum, int lower, int upper, int lo, int hi, vector<long>& tmp) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int left = mergeSort(prefixSum, lower, upper, lo, mid, tmp);
        int right = mergeSort(prefixSum, lower, upper, mid + 1, hi, tmp);

        int count = 0;
        int i = lo;
        int l = mid + 1;
        int r = mid + 1;
        while (i <= mid) {
            while (l <= hi && prefixSum[l] - prefixSum[i] < lower) l++;
            while (r <= hi && prefixSum[r] - prefixSum[i] <= upper) r++;
            count += (r - l);
            i++;
        }

        merge(prefixSum, lo, mid, hi, tmp);

        return left + right + count;
    }

    void merge(vector<long>& prefixSum, int lo, int mid, int hi, vector<long>& tmp) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = prefixSum[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) prefixSum[k] = tmp[j++];
            else if (j == hi + 1) prefixSum[k] = tmp[i++];
            else if (tmp[i] <= tmp[j]) prefixSum[k] = tmp[i++];
            else prefixSum[k] = tmp[j++];
        }
    }
};