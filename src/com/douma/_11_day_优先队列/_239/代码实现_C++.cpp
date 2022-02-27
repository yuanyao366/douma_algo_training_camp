class Solution {
public:
    // 优先队列 (大顶堆)
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    vector<int> maxSlidingWindow1(vector<int>& nums, int k) {
        // tips：这里维护的是大顶堆
        // 两个元素值不想等的话，那么元素大的放在前面，
        // 如果两个元素值相等的话，坐标大的放在前面，这样坐标 小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了，
        // 其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
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
            // 说明：这里的 while 可以使用 if 来代替，因为：
            // 要维护一个大小为 k 的窗口的话，每次最多只需要处理一个元素即可
            if (!d.empty() && d.front() <= i - k) {
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