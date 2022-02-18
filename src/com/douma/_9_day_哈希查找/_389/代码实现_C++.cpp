class Solution {
public:
    char findTheDifference(string s, string t) {
        int ret = 0;
        for (char c : s) ret ^= c;
        for (char c : t) ret ^= c;
        return ret;
    }

    char findTheDifference2(string s, string t) {
        int as = 0, at = 0;
        for (char c : s) as += c;
        for (char c : t) at += c;
        return at - as;
    }

    char findTheDifference1(string s, string t) {
        vector<int> count(26);
        for (char c : s) count[c - 'a']++;
        for (char c : t) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) return c;
        }
        return ' ';
    }
};