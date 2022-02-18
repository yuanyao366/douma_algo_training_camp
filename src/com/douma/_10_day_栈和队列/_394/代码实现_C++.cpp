class Solution {
public:
    string decodeString(string s) {
        string res;
        stack<int> numStack;
        stack<string> strStack;

        int num = 0;
        for (char c : s) {
            if (isdigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(res);
                num = 0;
                res = "";
            } else if (c == ']') {
                string item = res;
                for (int i = 1; i < numStack.top(); i++) {
                    res += item;
                }
                res = strStack.top() + res;
                numStack.pop();
                strStack.pop();
            } else {
                res.push_back(c);
            }
        }

        return res;
    }
};