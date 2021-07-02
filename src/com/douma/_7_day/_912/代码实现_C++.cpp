class Solution {
public:
    // 归并排序
    vector<int> sortArray(vector<int>& nums) {
        vector<int> tmp((int)nums.size(), 0);
        mergeSort(nums, 0, (int)nums.size() - 1, tmp);
        return nums;
    }

    void mergeSort(vector<int>& nums, int lo, int hi, vector<int>& tmp) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, lo, mid, tmp);
        mergeSort(nums, mid + 1, hi, tmp);

        merge(nums, lo, mid, hi, tmp);
    }

    void merge(vector<int>& nums, int lo, int mid, int hi, vector<int>& tmp) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) nums[k] = tmp[j++];
            else if (j == hi + 1) nums[k] = tmp[i++];
            else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++];
            else nums[k] = tmp[j++];
        }
    }

    // 快速排序
    vector<int> sortArray1(vector<int>& nums) {
        quickSort(nums, 0, (int)nums.size() - 1);
        return nums;
    }

    void quickSort(vector<int>& nums, int lo, int hi) {
        if (lo >= hi) return;
        int index = partition(nums, lo, hi);
        quickSort(nums, lo, index - 1);
        quickSort(nums, index + 1, hi);
    }

    int partition(vector<int>& nums, int lo, int hi) {
        int i = rand() % (hi - lo + 1) + lo;
        swap(nums[i], nums[hi]);
        int pivot = nums[hi];
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            if (nums[great] < pivot) {
                swap(nums[less], nums[great]);
                less++;
            }
        }
        swap(nums[less], nums[hi]);
        return less;
    }

    // 2. 快速排序 - 三路切分
    vector<int> sortArray(vector<int>& nums) {
        quickSort(nums, 0, (int)nums.size() - 1);
        return nums;
    }

    void quickSort(vector<int>& nums, int lo, int hi) {
        if (lo >= hi) return;
        int i = rand() % (hi - lo + 1) + lo;
        swap(nums[i], nums[hi]);

        int pivot = nums[hi];

        int less = lo;
        int great = hi;

        i = lo;
        while (i <= great) {
            if (nums[i] < pivot) {
                swap(nums[i], nums[less]);
                less++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums[i], nums[great]);
                great--;
            } else {
                i++;
            }
        }

        quickSort(nums, lo, less - 1);
        quickSort(nums, great + 1, hi);
    }
};