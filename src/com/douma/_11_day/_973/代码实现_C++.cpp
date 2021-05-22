class Solution {
public:
    static bool cmp(vector<int>& point1, vector<int>& point2) {
        return (point1[0] * point1[0] + point1[1] * point1[1]) < (point2[0] * point2[0] + point2[1] * point2[1]);
    }

    // 大顶堆
    vector<vector<int>> kClosest1(vector<vector<int>>& points, int k) {
        priority_queue<vector<int>, vector<vector<int>>, decltype(&cmp)> pq(cmp);
        for (vector<int> point : points) {
            pq.push(point);
            if (pq.size() > k) pq.pop();
        }
        // 小顶堆中存储的就是出现了前 k 个高频的元素
        vector<vector<int>> res(k, vector<int>(2));
        int index = 0;
        while (!pq.empty()) {
            res[index++] = pq.top();
            pq.pop();
        }
        return res;
    }

    // 快速排序分区优化
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        int left = 0, right = points.size() - 1;
        int target = points.size() - k;
        while (true) {
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        vector<vector<int>> ans(k, vector<int>(2));
        int index = 0;
        for (int i = points.size() - k; i < points.size(); i++) {
            ans[index++] = points[i];
        }
        return ans;
    }

    int partition(vector<vector<int>>& nums, int lo, int hi) {
        int i = rand() % (hi - lo + 1) + lo;
        swap(nums[i], nums[hi]);
        int pivot = nums[hi][0] * nums[hi][0] + nums[hi][1] * nums[hi][1];
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            int num = nums[great][0] * nums[great][0] + nums[great][1] * nums[great][1];
            // 降序排列
            if (num >= pivot) {
                swap(nums[less], nums[great]);
                less++;
            }
        }
        swap(nums[less], nums[hi]);
        return less;
    }
};