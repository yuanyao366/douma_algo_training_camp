class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        unordered_set<char> set;
        for (char c : jewels) set.insert(c);
        int ans = 0;
        for (char c : stones) {
            if (set.count(c)) ans++;
        }
        return ans;
    }
};