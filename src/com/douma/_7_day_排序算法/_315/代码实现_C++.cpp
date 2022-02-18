class Solution {
private:
    vector<int> tmp;
    vector<int> indexes;
    vector<int> tmpIndexes;
    vector<int> ans;

public:
    vector<int> countSmaller(vector<int>& nums) {
        int n = nums.size();
        tmp.resize(n, 0);
        indexes.resize(n, 0);
        for (int i = 0; i < n; i++) indexes[i] = i;
        tmpIndexes.resize(n, 0);
        ans.resize(n, 0);
        mergeSort(nums, 0, n - 1);
        return ans;
    }

    void mergeSort(vector<int>& nums, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    void merge(vector<int>& nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
            tmpIndexes[i] = indexes[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j];
                indexes[k] = tmpIndexes[j];
                j++;
            } else if (j == hi + 1) {
                nums[k] = tmp[i];
                indexes[k] = tmpIndexes[i];
                ans[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else if (tmp[i] <= tmp[j]) {
                nums[k] = tmp[i];
                indexes[k] = tmpIndexes[i];
                ans[tmpIndexes[i]] += (j - mid - 1);
                i++;
            } else {
                nums[k] = tmp[j];
                indexes[k] = tmpIndexes[j];
                j++;
            }
        }
    }
};