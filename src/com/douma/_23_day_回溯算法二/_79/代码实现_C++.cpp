class Solution {
private:
    vector<vector<char>> board;
    int m, n;
    string word;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

public:
    bool exist(vector<vector<char>>& board, string word) {
        this->board = board;
        this->m = (int)board.size();
        this->n = (int)board[0].size();
        this->visited = vector<vector<bool>>(m, vector<bool>(n, false));
        this->word = word;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == word[0]) {
                    if(dfs(row, col, 0)) return true;
                }
            }
        }

        return false;
    }

    bool dfs(int row, int col, int index) {
        if (board[row][col] != word[index]) return false;
        else if (index == word.length() - 1) return true;

        visited[row][col] = true;
        // 这里如果使用 vector 会超时
        for (auto& dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && !visited[nextRow][nextCol]) {
                if (dfs(nextRow, nextCol, index + 1)) return true;
            }
        }
        visited[row][col] = false;
        return false;
    }
};