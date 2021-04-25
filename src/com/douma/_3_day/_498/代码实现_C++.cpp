public:
    vector<int> findDiagonalOrder(vector<vector<int>>& matrix) {
        if (matrix.size() == 0) return vector<int>(0);
        int m = matrix.size();
        int n = matrix[0].size();

        vector<int> result(m * n);
        int row = 0, col = 0, d = 0;
        int dirs[2][2] = {{-1, 1}, {1, -1}};

        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];

            if (row >= m) {row = m - 1; col += 2; d = 1 - d;}
            if (col >= n) {col = n - 1; row += 2; d = 1 - d;}
            if (row < 0) {row = 0; d = 1 - d;}
            if (col < 0) {col = 0; d = 1 - d;}
        }

        return result;
    }