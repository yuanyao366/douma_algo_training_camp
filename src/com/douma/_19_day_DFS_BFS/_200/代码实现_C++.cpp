class Solution {
private:
    vector<vector<char>> grid;
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

public:
    int numIslands(vector<vector<char>>& grid) {
        this->grid = grid;
        this->rows = grid.size();
        this->cols = grid[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));

        int ans = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    dfs(row, col);
                    ans++;
                }
            }
        }

        return ans;
    }

    void dfs(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == '0' || visited[row][col]) {
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