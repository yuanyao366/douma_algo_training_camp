class Solution {
public:
    int calculate(string s) {
        stack<int> st;
        int result = 0;
        int sign = 1;
        int num = 0;
        for (char c : s) {
            if (c <= '9' && c >= '0') {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * num;
                sign = 1;
                num = 0;
            } else if (c == '-') {
                result += sign * num;
                sign = -1;
                num = 0;
            } else if (c == '(') {
                st.push(result);
                st.push(sign);
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * num;
                result *= st.top();
                st.pop();
                result += st.top();
                st.pop();
                num = 0;
            }
        }
        return result + sign * num;
    }
};