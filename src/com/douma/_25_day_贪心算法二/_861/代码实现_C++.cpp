class Solution {
public:
    int matrixScore(vector<vector<int>>& A) {
        int rows = A.size(), cols = A[0].size();
        // 使得每一行都从 1 开头
        for (int row = 0; row < rows; row++) {
            if (A[row][0] == 0) {
                // 行翻转
                for (int col = 0; col < cols; col++) {
                    A[row][col] ^= 1;
                }
            }
        }

        int res = 0;
        // 1 的数量越多，得到的数值越大
        for (int col = 0; col < cols; col++) {
            int cnt = 0;
            // 统计第 col 列有多少个 1。
            for (int row = 0; row < rows; row++) {
                cnt += A[row][col];
            }

            int maxOneCnt = max(cnt, rows - cnt);
            res += maxOneCnt * (1 << (cols - col - 1));
        }

        return res;
    }
};