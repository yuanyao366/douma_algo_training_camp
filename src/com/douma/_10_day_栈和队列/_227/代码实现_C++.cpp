class Solution {
public:
    int calculate(string s) {
        stack<int> st;
        char preSign = '+';
        int i = 0;
        while (i < s.length()) {
            if (s[i] ==' ') {
                i++;
            } else {
                int num = 0;
                while (i < s.length() && s[i] <= '9' && s[i] >= '0') {
                    num = num * 10 + (s[i] - '0');
                    i++;
                }
                if (preSign == '+') {
                    st.push(num);
                } else if (preSign == '-') {
                    st.push(-num);
                } else if (preSign == '*') {
                    int tmp = st.top();
                    st.pop();
                    st.push(tmp * num);
                } else if (preSign == '/') {
                    int tmp = st.top();
                    st.pop();
                    st.push(tmp / num);
                }

                while (i < s.length() && s[i] == ' ') i++;
                if (i < s.length()) preSign = s[i];
                i++;
            }
        }
        int res = 0;
        while (!st.empty()) {
            res += st.top();
            st.pop();
        }
        return res;
    }
};