class Solution {
private:
    unordered_map<char, string> phone = {
        {'2', "abc"},
        {'3', "def"},
        {'4', "ghi"},
        {'5', "jkl"},
        {'6', "mno"},
        {'7', "pqrs"},
        {'8', "tuv"},
        {'9', "wxyz"}
    };
    vector<string> res;

public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) return res;
        findCombination(digits, 0, "");
        return res;
    }

    void findCombination(string digits, int index, string combination) {
        if (index == digits.length()) {
            res.push_back(combination);
            return;
        }

        string letters = phone[digits[index]];
        for (char c : letters) {
            findCombination(digits, index + 1, combination + c);
        }
    }
};