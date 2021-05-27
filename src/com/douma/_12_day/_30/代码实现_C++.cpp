class Solution {
public:
    // 滑动窗口(O(n))
    vector<int> findSubstring(string s, vector<string>& words) {
        int oneWordLen = words[0].length();
        if (s.length() < oneWordLen) return {};

        int wordNum = words.size();

        unordered_map<string, int> map;
        for (string word : words) {
            map[word]++;
        }

        vector<int> ans;
        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i;
            int matchedWords = 0;
            unordered_map<string, int> windowMap;
            while (right <= s.length() - oneWordLen) {
                string currWord = s.substr(right, oneWordLen);
                windowMap[currWord]++;
                matchedWords++;
                while (windowMap[currWord] > map[currWord]) {
                    string leftWord = s.substr(left, oneWordLen);
                    windowMap[leftWord]--;
                    left += oneWordLen;
                    matchedWords--;
                }
                if (matchedWords == wordNum) ans.push_back(left);
                right += oneWordLen;
            }
        }
        return ans;
    }
};