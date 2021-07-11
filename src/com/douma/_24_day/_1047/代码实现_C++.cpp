class Solution {
public:
    // 栈
    string removeDuplicates(string s) {
        vector<char> stack;
        for (char c : s) {
            if (!stack.empty() && stack[stack.size() - 1] == c) {
                stack.pop_back();
            } else {
                stack.push_back(c);
            }
        }
        string res = "";
        for (char c : stack) res += c;
        return res;
    }

    // 快慢指针
    string removeDuplicates2(string s) {
        int slow = -1, fast = 0;
        while (fast < s.length()) {
            if (slow >= 0 && s[slow] == s[fast]) {
                slow--;
            } else {
                slow++;
                s[slow] = s[fast];
            }
            fast++;
        }
        return s.substr(0, slow + 1);
    }
};