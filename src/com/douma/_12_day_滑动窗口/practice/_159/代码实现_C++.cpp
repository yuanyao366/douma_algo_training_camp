class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
        int ans = 0;

        int left = 0, right = 0;
        unordered_map<char, int> windowCharCnt;
        while (right < s.length()) {
            char currChar = s[right];
            windowCharCnt[currChar]++;
            while (left <= right && windowCharCnt.size() > 2) {
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