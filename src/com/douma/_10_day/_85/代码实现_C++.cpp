class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        int m = matrix.size();
        if (m == 0) return 0;
        int n = matrix[0].size();

        vector<vector<int>> left(m, vector<int>(n, 0));
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1') {
                    left[row][col] = (col == 0 ? 0 : left[row][col - 1]) + 1;
                }
            }
        }

        int ans = 0;
        for (int col = 0; col < n; col++) {
            vector<int> up(m);
            vector<int> down(m, m);
            stack<int> st;
            for (int row = 0; row < m; row++) {
                while (!st.empty() && left[row][col] <= left[st.top()][col]) {
                    down[st.top()] = row;
                    st.pop();
                }
                up[row] = (st.empty() ? -1 : st.top());
                st.push(row);
            }

            for (int row = 0; row < m; row++) {
                int height = left[row][col];
                int width = down[row] - up[row] - 1;
                ans = max(ans, height * width);
            }
        }

        return ans;
    }
};