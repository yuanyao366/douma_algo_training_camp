class Solution {
public:

    static bool cmp(pair<int, int>& m, pair<int, int>& n) {
        return m.second > n.second;
    }

    // 小顶堆
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(n)
    vector<int> topKFrequent1(vector<int>& nums, int k) {
        unordered_map<int, int> count;
        for (int num : nums) {
            count[num]++;
        }

        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(&cmp)> pq(cmp);
        for (auto& [num, cnt] : count) {
            pq.emplace(num, cnt);
            if (pq.size() > k) {
                pq.pop();
            }
        }

        vector<int> res;
        while (!pq.empty()) {
            res.emplace_back(pq.top().first);
            pq.pop();
        }
        return res;
    }

    // 快速排序分区优化
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> count;
        for (int num : nums) {
            count[num]++;
        }

        vector<pair<int, int>> uniqueNums;
        for (auto& kv: count) {
            uniqueNums.push_back(kv);
        }

        int left = 0, right = uniqueNums.size() - 1;
        int target = uniqueNums.size() - k;
        while (true) {
            int pivotIndex = partition(uniqueNums, left, right);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        vector<int> ans(k, 0);
        int index = 0;
        for (int i = uniqueNums.size() - k; i < uniqueNums.size(); i++) {
            ans[index++] = uniqueNums[i].first;
        }
        return ans;
    }

    int partition(vector<pair<int, int>>& nums, int lo, int hi) {
        int i = rand() % (hi - lo + 1) + lo;
        swap(nums[i], nums[hi]);
        int pivot = nums[hi].second;
        int less = lo;
        int great = lo;
        for (; great <= hi - 1; great++) {
            if (nums[great].second < pivot) {
                swap(nums[less], nums[great]);
                less++;
            }
        }
        swap(nums[less], nums[hi]);
        return less;
    }
};