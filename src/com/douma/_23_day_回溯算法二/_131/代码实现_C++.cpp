class Solution {
private:
    vector<vector<string>> res;
    string s;
public:
    vector<vector<string>> partition(string s) {
        this->s = s;
        vector<string> path;
        dfs(0, path);
        return res;
    }

    void dfs(int startIndex, vector<string>& path) {
        if (startIndex == s.length()) {
            res.emplace_back(path);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (!checkPalindrome(s, startIndex, i)) continue;
            path.push_back(s.substr(startIndex, i - startIndex + 1));
            dfs(i + 1, path);
            path.pop_back();
        }
    }

    bool checkPalindrome(string str, int left, int right) {
        while (left < right) {
            if (str[left] != str[right]) return false;
            left++;
            right--;
        }
        return true;
    }
};