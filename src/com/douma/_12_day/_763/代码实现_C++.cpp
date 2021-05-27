class Solution {
public:
    vector<int> partitionLabels(string s) {
        vector<int> c2Index(26, -1);
        for (int i = 0; i < s.length(); i++) {
            c2Index[s[i] - 'a'] = i;
        }

        vector<int> ans;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s[i];
            right = max(right, c2Index[c - 'a']);

            if (i == right) {
                ans.push_back(right - left + 1);
                left = right + 1;
            }
        }

        return ans;
    }
};