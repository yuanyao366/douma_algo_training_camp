class Solution {
public:
    // 贪心 + 单调栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    string removeDuplicateLetters(string s) {
        // 1. 计算字符在字符串 s 中的最后索引
        vector<int> lastIndex(26);
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s[i] - 'a'] = i;
        }

        // 2. 维护单调栈，使用 vector 来模拟栈的功能
        vector<char> stack;
        // 用于记录字符是否已经存在于栈中
        vector<bool> exists(26);
        for (int i = 0; i < s.length(); i++) {
            if (exists[s[i] - 'a']) continue;

            // 条件：
            // (1). 当前字符小于栈顶字符
            // (2). 栈顶字符在当前字符的后面还会出现
            while (!stack.empty() && stack[stack.size() - 1] > s[i]
                        && lastIndex[stack[stack.size() - 1] - 'a'] > i) {
                char top = stack[stack.size() - 1];
                stack.pop_back();
                exists[top - 'a'] = false;
            }
            stack.push_back(s[i]);
            exists[s[i] - 'a'] = true;
        }

        // 3. 将栈中字符拼接成结果
        string res = "";
        for (char c : stack) res += c;
        return res;
    }
};