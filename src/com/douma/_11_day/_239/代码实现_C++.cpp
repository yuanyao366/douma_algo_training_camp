class Solution {
public:
    // 优先队列 (大顶堆)
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    vector<int> maxSlidingWindow1(vector<int>& nums, int k) {
        priority_queue<pair<int, int>> pq;
        for (int i = 0; i < k; i++) pq.emplace(nums[i], i);

        vector<int> ans = {pq.top().first};
        for (int i = k; i < nums.size(); i++) {
            pq.emplace(nums[i], i);
            while (pq.top().second <= i - k) {
                pq.pop();
            }
            ans.push_back(pq.top().first);
        }
        return ans;
    }

    // 双端队列
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        deque<int> d;
        for (int i = 0; i < nums.size(); i++) {
            while (!d.empty() && d.front() <= i - k) {
                d.pop_front();
            }
            while (!d.empty() && nums[i] > nums[d.back()]) {
                d.pop_back();
            }
            d.push_back(i);
            if (i >= k - 1) ans.push_back(nums[d.front()]);
        }
        return ans;
    }
};