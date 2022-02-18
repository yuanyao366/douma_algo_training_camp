class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> map;
        for (string& str : strs) {
            vector<int> count(26);
            for (char c : str) count[c - 'a']++;
            string key = "";
            for (int i = 0; i < 26; i++) key += count[i];
            map[key].emplace_back(str);
        }
        vector<vector<string>> ans;
        for (auto it = map.begin(); it != map.end(); ++it) {
            ans.emplace_back(it->second);
        }
        return ans;
    }

    vector<vector<string>> groupAnagrams1(vector<string>& strs) {
        unordered_map<string, vector<string>> map;
        for (string& str : strs) {
            string key = str;
            sort(key.begin(), key.end());
            map[key].emplace_back(str);
        }
        vector<vector<string>> ans;
        for (auto it = map.begin(); it != map.end(); ++it) {
            ans.emplace_back(it->second);
        }
        return ans;
    }
};