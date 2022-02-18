class Solution {
public:
    // 小顶堆 + 大顶堆
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(min(k, n - k))
    int findKthLargest1(vector<int>& nums, int k) {
        int n = nums.size();
        if (k < n - k) {
            // 使用小顶堆
            priority_queue<int, vector<int>, greater<int>> pq;
            for (int i = 0; i < n; i++) {
                pq.push(nums[i]);
                if (pq.size() > k) {
                    pq.pop();
                }
            }
            return pq.top();
        } else {
            // 使用大顶堆
            priority_queue<int> pq;
            for (int i = 0; i < n; i++) {
                pq.push(nums[i]);
                if (pq.size() > n - k + 1) {
                    pq.pop();
                }
            }
            return pq.top();
        }
    }

    // 快速排序分区优化
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    int findKthLargest(vector<int>& nums, int k) {
        int left = 0, right = nums.size() - 1;
        int target = nums.size() - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
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
};