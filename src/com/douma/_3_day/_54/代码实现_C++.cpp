public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
            int m = matrix.size();
            int n = matrix[0].size();

            int dirs[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int row = 0, col = 0, di = 0;

            vector<int> res;
            vector<vector<bool>> seen(m, vector<bool>(n));

            for (int i = 0; i < m * n; i++) {
                res.push_back(matrix[row][col]);
                seen[row][col] = true;

                int nextRow = row + dirs[di][0];
                int nextCol = col + dirs[di][1];

                if (nextRow < 0 || nextRow >= m
                        || nextCol < 0 || nextCol >= n
                        || seen[nextRow][nextCol]) {
                    di = (di + 1) % 4;
                }

                row = row + dirs[di][0];
                col = col + dirs[di][1];
            }

            return res;
        }

// 方法二：按层模拟
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int startRow = 0, endRow = matrix.size() - 1;
        int startCol = 0, endCol = matrix[0].size() - 1;

        vector<int> res;
        while (startRow <= endRow && startCol <= endCol) {
            // top 行
            for (int col  = startCol; col <= endCol; col++) res.push_back(matrix[startRow][col]);
            // right 列
            for (int row = startRow + 1; row <= endRow; row++) res.push_back(matrix[row][endCol]);
            if (startRow < endRow && startCol < endCol) {
                // bottom 行
                for (int col = endCol - 1; col > startCol; col--) res.push_back(matrix[endRow][col]);
                // left 列
                for (int row = endRow; row > startRow; row--) res.push_back(matrix[row][startCol]);
            }
            startRow++;
            endRow--;
            startCol++;
            endCol--;
        }

        return res;
    }