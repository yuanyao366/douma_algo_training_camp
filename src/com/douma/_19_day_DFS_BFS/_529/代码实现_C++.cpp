class Solution {
private:
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        this->rows = board.size();
        this->cols = board[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            dfs(click[0], click[1], board);
        }

        return board;
    }

    void dfs(int row, int col, vector<vector<char>>& board) {
        if (!inArea(row, col) || board[row][col] != 'E' || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        int mines = 0;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (inArea(nextRow, nextCol) && board[nextRow][nextCol] == 'M') {
                mines++;
            }
        }

        if (mines > 0) board[row][col] = mines + '0';
        else {
            board[row][col] = 'B';
            for (vector<int> dir: dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                dfs(nextRow, nextCol, board);
            }
        }
    }
};