class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.length() != t.length()) return false;
        unordered_map<char, int> map;
        for (char c : s) {
            if (map.count(c)) {
                int count = map[c];
                map[c] = count + 1;
            } else {
                map[c] = 1;
            }
        }

        for (char c : t) {
            if (!map.count(c) || map[c] <= 0) {
                return false;
            }
            map[c] = map[c] - 1;
        }

        return true;
    }
    bool isAnagram1(string s, string t) {
        if (s.length() != t.length()) return false;
        vector<int> map(26);
        for (char c : s) map[c - 'a']++;
        for (char c : t){
            if (map[c - 'a'] <= 0) return false;
            map[c - 'a']--;
        }
        return true;
    }
};