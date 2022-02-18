class Solution {
public:
    int maximumGap(vector<int>& nums) {
        if (nums.size() < 2) return 0;

        // 1. 找到最大最小值
        int minValue = nums[0];
        int maxValue = nums[0];
        for (int num : nums) {
            minValue = min(minValue, num);
            maxValue = max(maxValue, num);
        }
        if (maxValue == minValue) return 0;

        int gap = (int)ceil((double)(maxValue - minValue)/(nums.size() - 1));

        // 2. 初始化桶数组
        int bucketNum = nums.size();
        int buckets[bucketNum][2];
        for (int i = 0; i < bucketNum; i++) {
            buckets[i][0] = INT_MAX;
            buckets[i][1] = INT_MIN;
        }

        // 3. 将所有元素添加到对应的桶中
        for (int num : nums) {
            // bucketId 计算逻辑如何理解，请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I498BD
            int bucketId = (num - minValue) / gap;
            buckets[bucketId][0] = min(buckets[bucketId][0], num);
            buckets[bucketId][1]= max(buckets[bucketId][1], num);
        }

        // 4. 计算最大间隔
        int maxGap = 0;
        int prevBucketMax = minValue;
        for (auto& bucket : buckets) {
            if (bucket[0] == INT_MAX) continue;
            maxGap = max(maxGap, bucket[0] - prevBucketMax);
            prevBucketMax = bucket[1];
        }

        return maxGap;
    }
};