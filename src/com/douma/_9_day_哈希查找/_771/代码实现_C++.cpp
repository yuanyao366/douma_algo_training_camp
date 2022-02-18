class Solution {
public:
    int numJewelsInStones1(string jewels, string stones) {
        unordered_set<char> set;
        for (char c : jewels) set.insert(c);
        int ans = 0;
        for (char c : stones) {
            if (set.count(c)) ans++;
        }
        return ans;
    }

    int numJewelsInStones(string jewels, string stones) {
        // count 中存储 A 到 Z 中的所有的字符，包含 58 个字符
        int len = 'z' - 'A' + 1;
        vector<int> count(len);
        for (char c : jewels) count[c - 'A'] = 1;

        int ans = 0;
        for (char c : stones) {
            if (count[c - 'A'] == 1) ans++;
        }

        return ans;
    }
};