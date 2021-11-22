class Solution {
public:
    // 哈希集合
    int lengthOfLongestSubstring1(string s) {
        int n = s.length();
        if (n <= 1) return n;

        int maxLen = 0;
        int left = 0, right = 0;
        unordered_set<char> window;
        while (right < n) {
            char currChar = s[right];
            if (!window.count(currChar)) {
                maxLen = max(maxLen, right - left + 1);
                window.insert(currChar);
                right++;
            } else {
                window.erase(s[left]);
                left++;
            }
        }
        return maxLen;
    }

    // 哈希映射
    int lengthOfLongestSubstring2(string s) {
        int n = s.length();
        if (n <= 1) return n;

        int maxLen = 0;
        int left = 0, right = 0;
        // 存储窗口中每个字符及其位置的下一个位置
        // 表示如果再次碰到这个字符的时候，那么 left 就跳到这个字符所在位置的下一个位置
        unordered_map<char, int> window;
        while (right < n) {
            char currChar = s[right];
            unordered_map<char, int>::iterator it = window.find(currChar);
            int currCharIndex = (it == window.end() ? -1 : it->second);
            // 下面这样代码的详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P
            left = max(left, currCharIndex);
            maxLen = max(maxLen, right - left + 1);

            window[currChar] = right + 1;
            right++;
        }
        return maxLen;
    }

    // 字符数组
    int lengthOfLongestSubstring(string s) {
        int n = s.length();
        if (n <= 1) return n;

        int maxLen = 0;
        int left = 0, right = 0;
        vector<int> window(128, 0);
        while (right < n) {
            char currChar = s[right];
            int currCharIndex = window[currChar];
            left = max(left, currCharIndex);
            maxLen = max(maxLen, right - left + 1);

            window[currChar] = right + 1;
            right++;
        }
        return maxLen;
    }
};