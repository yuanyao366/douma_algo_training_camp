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

                    int boxIndex = row / 3 + (col / 3) * 3;
                    if (boxUsed[boxIndex][num]) return false;
                    else boxUsed[boxIndex][num] = true;
                }
            }
        }

        return true;
    }