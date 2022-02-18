class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        int ans = 0;

        int left = 0, right = 0;
        unordered_map<char, int> windowCharCnt;
        while (right < s.length()) {
            char currChar = s[right];
            windowCharCnt[currChar]++;
            while (left <= right && windowCharCnt.size() > k) {
                char leftChar = s[left];
                windowCharCnt[leftChar]--;
                if (windowCharCnt[leftChar] <= 0) {
                    windowCharCnt.erase(leftChar);
                }
                left++;
            }
            ans = max(ans, right - left + 1);
            right++;
        }

        return ans;
    }
};