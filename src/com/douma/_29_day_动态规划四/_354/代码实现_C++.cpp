class Solution {
public:
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        sort(envelopes.begin(), envelopes.end(), [](const auto& o1, const auto& o2) {
            return o1[0] < o2[0] || (o1[0] == o2[0] && o1[1] > o2[1]);
        });

        int n = envelopes.size();
        vector<int> dp(n, 1);
        int ans = 1;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (envelopes[j][1] > envelopes[i][1]) {
                    dp[j] = max(dp[i] + 1, dp[j]);
                    ans = max(ans, dp[j]);
                }
            }
        }

        return ans;
    }

     /*
    俄罗斯套娃这道题的输入改掉了，之前的输入数据规模是 5000 ，所以时间复杂度为 O(n^2) 的动态规划方案是可以的

    现在的输入数据规模改为 10^5 ，所以，时间复杂度为 O(n^2) 会超时了
    现在题目的输入数据规模为：
    1 <= envelopes.length <= 10^5
    envelopes[i].length == 2
    1 <= wi, hi <= 10^5
     */
    // 所以需要使用二分解法
    // 时间复杂度 O(nlogn)
    // 空间复杂度 O(n)
    int maxEnvelopes(vector<vector<int>>& envelopes) {
        // 先按照宽度升序排列，宽度相同的话，按照高度降序排列
        sort(envelopes.begin(), envelopes.end(), [](const auto& o1, const auto& o2) {
            return o1[0] < o2[0] || (o1[0] == o2[0] && o1[1] > o2[1]);
        });

        int n = envelopes.size();

        // res 用于存储所有可套娃信封的高度值
        // 保证 res 中的元素肯定是升序排列的
        vector<int> res;

        // 1. 将宽度最小的信封的高度值放在 res 中
        res.push_back(envelopes[0][1]);

        // 2. 从第二个信封开始，遍历并处理每一个信封
        for (int i = 1; i < n; ++i) {
            int currHeight = envelopes[i][1];
            // 2.1 如果当前信封的高度大于 res 中最后一个信封的高度
            if (currHeight > res[res.size() - 1]) {
                // 那么，可以将当前的信封放入到 res 中
                // (当前信封的宽度肯定大于等于果集中最后一个信封的宽度，因为是按照宽度升序排列的)
                res.push_back(currHeight);
            } else { // 2.2 当前信封的高度小于等于 res 中最后一个信封的高度
                // 将当前信封插入到 res 合适的位置上
                // 先使用二分查找，在 res 中查找 currHeight 合适的位置
                int index = binarySearch(res, currHeight);
                // 将当前信封替换之前这个位置上的信封
                // 之所以可以将当前信封替换掉第 index 处的信封，是因为：
                // ① 当前信封的宽度大于等于第 index - 1 处的信封的宽度
                // ② 当前信封的高度大于第 index - 1 处的信封的高度
                res[index] = currHeight;
            }
        }
        return res.size();
    }

    int binarySearch(vector<int>& res, int target) {
        int low = 0, high = res.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (res[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
};