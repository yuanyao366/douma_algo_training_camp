class Solution {
public:
    int longestPalindrome(string s) {
        vector<int> count(128);
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            count[s[i]]++;
        }

        int ans = 0;
        for (int v: count) {
            // 贪心策略：每次将这个字母的 v / 2 数放在回文串的左右两侧
            ans += v / 2 * 2;
            // 中间只能有一个出现奇数次的字符
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }

        return ans;
    }
};