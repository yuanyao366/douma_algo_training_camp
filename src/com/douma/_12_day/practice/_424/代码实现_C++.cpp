class Solution {
public:
    int characterReplacement(string s, int k) {
        int n = s.size();
        if (n < 2) {
            return n;
        }

        int res = 0;

        int left = 0, right = 0;
        // 统计当前窗口中每个字符出现的次数
        vector<int> freq(26);
        // 存储当前窗口中出现最多字符的出现次数
        int maxCount = 0;
        while (right < n) {
            freq[s[right] - 'A']++;
            maxCount = max(maxCount, freq[s[right] - 'A']);
            // 出现最多的次数，再加上 k 都没有当前窗口长度大，说明可以缩减窗口了
            if ((right - left + 1) - maxCount > k) {
                freq[s[left] - 'A']--;
                left++;
            }
            res = max(res, right - left + 1);

            right++;
        }

        return res;
    }
};