public:
    int totalHammingDistance(vector<int>& nums) {
        int n = nums.size();
        // 我们考虑数组中每个数二进制的第 i 位，
        // 假设一共有 t 个 0 和 n - t 个 1，
        // 那么显然在第 i 位的汉明距离的总和为 t * (n - t)

        // 存储所有元素对应位的 1 的个数
        vector<int> cnt(32, 0);
        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                // 检查每个位是否为 1，并累加
                cnt[i] += (num & 1);
                num >>= 1;
                i++;
            }
        }

        int res = 0;
        for (int k : cnt) {
            res += k * (n - k);
        }
        return res;
    }