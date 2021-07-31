class Solution {
public:
    // 1. 动态规划
    int longestValidParentheses1(string s) {
        int n = s.length();
        if (n <= 1) return 0;

        vector<int> dp(n);

        dp[0] = 0;
        if (s[0] == '(' && s[1] == ')') dp[1] = 2;

        int ans = dp[1];

        for (int i = 2; i < n; i++) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (i - dp[i - 1] >= 1 && s[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                ans = max(ans, dp[i]);
            }
        }

        return ans;
    }

    // 2. 栈
    int longestValidParentheses2(string s) {
        int n = s.length();
        if (n <= 1) return 0;
        int ans = 0;
        stack<int> st;
        st.push(-1);
        for (int i = 0; i < n; i++) {
            if (s[i] == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.empty()) {
                    st.push(i);
                } else {
                    ans = max(ans, i - st.top());
                }
            }
        }
        return ans;
    }

    // 3. 双指针
    int longestValidParentheses(string s) {
        int n = s.length();
        if (n <= 1) return 0;
        int ans = 0;
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '(') left++;
            else if (s[i] == ')') right++;

            if (left == right) {
                ans = max(ans, 2 * left);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == '(') left++;
            else if (s[i] == ')') right++;

            if (left == right) {
                ans = max(ans, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return ans;
    }
};