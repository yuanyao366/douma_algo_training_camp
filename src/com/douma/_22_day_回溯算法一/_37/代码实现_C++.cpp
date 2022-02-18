class Solution {
private:
    bool rowUsed[9][10];
    bool colUsed[9][10];
    bool boxUsed[3][3][10];

public:
    void solveSudoku(vector<vector<char>>& board) {
        // 初始化
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                int num = board[i][j] - '0';
                if (num >= 1 && num <= 9) {
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[i / 3][j / 3][num] = true;
                }
            }
        }
        // bug 修复，需要调用回溯的方法
        backTrack(board, 0, 0);
    }

    bool backTrack(vector<vector<char>>& board, int row, int col) {
        if (col == board[0].size()) {
            // 下一行
            row++;
            // 第一列
            col = 0;
            if (row == board.size()) {
                return true;
            }
        }

        if (board[row][col] == '.') {
            // 尝试填充 1 到 9 数字
            for (int num = 1; num <= 9; num++) {
                bool canPlaced = !(rowUsed[row][num]
                        || colUsed[col][num]
                        || boxUsed[row / 3][col / 3][num]);
                if (canPlaced) { // 填充当前的空格
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    board[row][col] = (char)('0' + num);
                    // 尝试填充下一个空格，如果填充成功的话，则返回 true
                    if (backTrack(board, row, col + 1)) {
                        return true;
                    }

                    // 否则的话，回溯
                    board[row][col] = '.';
                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else { // 跳过这个空格
            return backTrack(board, row, col + 1);
        }

        return false;
    }
};