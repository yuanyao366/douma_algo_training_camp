class Solution {
public:
    int maxProduct(vector<string>& words) {
        int m = words.size();
        unordered_map<int, int> bitMaskMap;
        for (int i = 0; i < m; i++) {
            int bitMask = 0;
            for (char c : words[i]) {
                bitMask |= 1 << (c - 'a');
            }
            if (bitMaskMap.count(bitMask)) {
                int maxV = max(bitMaskMap[bitMask], (int)words[i].size());
                bitMaskMap[bitMask] = maxV;
            } else {
                bitMaskMap[bitMask] = words[i].size();
            }
        }

        int ans = 0;
        for (auto& x : bitMaskMap) {
            for (auto& y : bitMaskMap) {
                if ((x.first & y.first) == 0) {
                    ans = max(ans, x.second * y.second);
                }
            }
        }

        return ans;
    }
};