class Solution {
public:
    bool wordPattern(string pattern, string s) {
        vector<string> tokens;
        split(s, tokens);
        if (pattern.length() != tokens.size()) return false;

        unordered_map<char, string> char2Word;
        unordered_map<string, char> word2Char;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern[i];
            string word = tokens[i];
            if ((char2Word.count(c) && char2Word[c] != word) ||
                 (word2Char.count(word) && word2Char[word] != c)) {
                return false;
            }
            char2Word[c] = word;
            word2Char[word] = c;
        }
        return true;
    }

    void split(const string& s, vector<string>& tokens, char delim = ' ') {
        tokens.clear();
        auto string_find_first_not = [s, delim](size_t pos = 0) -> size_t {
            for (size_t i = pos; i < s.size(); i++) {
                if (s[i] != delim) return i;
            }
            return string::npos;
        };
        size_t lastPos = string_find_first_not(0);
        size_t pos = s.find(delim, lastPos);
        while (lastPos != string::npos) {
            tokens.emplace_back(s.substr(lastPos, pos - lastPos));
            lastPos = string_find_first_not(pos);
            pos = s.find(delim, lastPos);
        }
    }
};