public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();

        bool flagcol1 = false;
        for (int row = 0; row < m; row++) {
            // 计算第一列是否包含 0
            if (matrix[row][0] == 0) flagcol1 = true;

            // 设置第一列和第一行
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = matrix[row][0] = 0;
                }
            }
        }

        // 为了不提前覆盖 maxtrix[0][0]，所以从最后一行开始遍历处理
        for (int row = m - 1; row >= 0; row--) {
            for (int col = 1; col < n; col++) {
                if (matrix[0][col] == 0 || matrix[row][0] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (flagcol1) matrix[row][0] = 0;
        }
    }