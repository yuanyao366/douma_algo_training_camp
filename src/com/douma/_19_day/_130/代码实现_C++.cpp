class Solution {
private:
    vector<vector<char>> board;
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
public:
    void solve(vector<vector<char>>& board) {
        this->board = board;
        this->rows = board.size();
        this->cols = board[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));

        // 从四周查找 O 字母
        for (int col = 0; col < cols; col++) {
            // 1. 第一行
            if (board[0][col] == 'O' && !visited[0][col]) {
                dfs(0, col);
            }

            // 2. 最后一行
            if (board[rows - 1][col] == 'O' && !visited[rows - 1][col]) {
                dfs(rows - 1, col);
            }
        }

        for (int row = 1; row < rows - 1; row++) {
            // 3. 第一列
            if (board[row][0] == 'O' && !visited[row][0]) {
                dfs(row, 0);
            }

            // 4. 最后一列
            if (board[row][cols - 1] == 'O' && !visited[row][cols - 1]) {
                dfs(row, cols - 1);
            }
        }

        // 将没有被访问过并且等于 O 的字母填充为 X
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (board[row][col] == 'O' && !visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }
    }

    void dfs(int row, int col) {
        if (!inArea(row, col) || board[row][col] == 'X' || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol);
        }
    }

};