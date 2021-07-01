class Solution {
public:
    /*
    [2147483647,-1,2147483647]
    1
    2147483647

    注意越界
    */
    // 滑动窗口 + 有序集合
    // 时间复杂度：O(nlog(min(n, k)))
    // 空间复杂度：O(min(n, k))
    bool containsNearbyAlmostDuplicate1(vector<int>& nums, int k, int t) {
        int left = 0, right = 0;

        set<long long> window;
        while (right < nums.size()) {
            long long x = nums[right];

            auto iter = window.lower_bound(x - t);
            if (iter != window.end() && *iter <= x + t) {
                return true;
            }

            window.insert(x);
            if (window.size() > k) {
                window.erase((long long)nums[left]);
                left++;
            }

            right++;
        }

        return false;
    }

    // 滑动窗口 + 桶
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        int left = 0, right = 0;
        long long bucketSize = (long long)t + 1;

        unordered_map<long long, long long> window;
        while (right < nums.size()) {
            long long x = nums[right];
            long long bucketId = getBucketId(x, bucketSize);

            if (window.count(bucketId)) return true;
            int leftBucketId = bucketId - 1, rightBucketId = bucketId + 1;
            if (window.count(leftBucketId) && x - window[leftBucketId] <= t) return true;
            if (window.count(rightBucketId) && window[rightBucketId] - x <= t) return true;

            window[bucketId] = x;

            if (window.size() > k) {
                window.erase(getBucketId((long long)nums[left], bucketSize));
                left++;
            }

            right++;
        }

        return false;
    }

    long long getBucketId(long long x, long long bucketSize) {
        if (x >= 0) {
            return x / bucketSize;
        }
        return (x + 1) / bucketSize - 1;
    }
};