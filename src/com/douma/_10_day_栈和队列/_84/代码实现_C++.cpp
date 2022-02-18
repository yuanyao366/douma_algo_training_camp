class Solution {
public:
    // 枚举高 + 单调栈优化 + 一次遍历
    int largestRectangleArea(vector<int>& heights) {
        int n = heights.size();

        vector<int> left(n);
        vector<int> right(n, n);
        stack<int> st;
        for (int i = 0; i < n; i++) {
            while (!st.empty() && heights[i] <= heights[st.top()]) {
                right[st.top()] = i;
                st.pop();
            }
            left[i] = (st.empty() ? -1 : st.top());
            st.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int width = right[mid] - left[mid] - 1;
            ans = max(ans, height * width);
        }

        return ans;
    }
};