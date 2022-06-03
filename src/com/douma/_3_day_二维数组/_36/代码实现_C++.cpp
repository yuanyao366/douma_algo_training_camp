public:
    bool isValidSudoku(vector<vector<char>>& board) {
        vector<vector<bool>> rowUsed(9, vector<bool>(9));
        vector<vector<bool>> colUsed(9, vector<bool>(9));
        vector<vector<bool>> boxUsed(9, vector<bool>(9));

        for (int row = 0; row < board.size(); row++) {
            for (int col = 0; col < board[0].size(); col++) {
                if (board[row][col] != '.') {
                    int num = board[row][col] - '1';

                    if (rowUsed[row][num]) return false;
                    else rowUsed[row][num] = true;

                    if (colUsed[col][num]) return false;
                    else colUsed[col][num] = true;

                    /*
                    boxIndex 是如何推导出来的呢？
                    首先，我们要明白一个知识点，那就是二维数组转一维数组
                    假设二维数组中有个元素，它的坐标索引是 (row, col)，
                    那么它在一维数组中的索引是：index = col + row * n，假设 n 是二维数组的总列数

                    对于 boxIndex，首先，我们将二维数组元素 (row, col)，
                    转成这个元素所在的 box 的坐标索引为 (row / 3, col / 3)
                    然后将二维的 3×3 的 box 数组转成一维的，boxIndex = col / 3 + (row / 3) * 3

                    在这里 row 和 col 是可以互换位置的，因为 box 数组是一个 3×3 的二维数组，行数和列数是一样的
                     */
                    int boxIndex = row / 3 + (col / 3) * 3;
                    if (boxUsed[boxIndex][num]) return false;
                    else boxUsed[boxIndex][num] = true;
                }
            }
        }

        return true;
    }