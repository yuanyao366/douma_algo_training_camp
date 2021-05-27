class Solution {
public:
    string minWindow(string s, string t) {
        vector<int> cntT(60, 0);
        int uniqueCharCnt = 0;
        for (char c : t) {
            if (cntT[c - 'A'] == 0) uniqueCharCnt++;
            cntT[c - 'A']++;
        }

        vector<pair<int, int>> filteredS;
        for (int i = 0; i < s.length(); i++) {
            if (cntT[s[i] - 'A'] > 0) {
                pair<int, char> p;
                p.first = i;
                p.second = s[i];
                filteredS.push_back(p);
            }
        }

        int left = 0, right = 0;
        vector<int> windowCntS(60, 0);
        int matchedChars = 0;
        vector<int> ans = {-1, 0};
        while (right < filteredS.size()) {
            char currChar = filteredS[right].second;
            int currCharIndex = currChar - 'A';
            windowCntS[currCharIndex]++;

            if (windowCntS[currCharIndex] == cntT[currCharIndex]) {
                matchedChars++;
            }

            while (left <= right && matchedChars == uniqueCharCnt) {
                // 尝试缩减窗口，因为我们想找到最短符合条件的子串
                int end = filteredS[right].first;
                int start = filteredS[left].first;
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                }

                char leftChar = filteredS[left].second;
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) matchedChars--;

                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substr(ans[1], ans[0]);
    }
};