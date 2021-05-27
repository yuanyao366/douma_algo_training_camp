class Solution {
public:
    // 滑动窗口
    bool checkInclusion(string s1, string s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) return false;

        vector<int> cnt(26, 0);
        for (char c : s1) {
            cnt[c - 'a']++;
        }

        int left = 0, right = 0;
        while (right < s2.length()) {
            int x = s2[right] - 'a';
            cnt[x]--;
            while (cnt[x] < 0) {
                cnt[s2[left] - 'a']++;
                left++;
            }
            if (right - left + 1 == n) return true;
            right++;
        }

        return false;
    }
};