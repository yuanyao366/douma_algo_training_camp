class Solution {
private:
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int currColor;

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

public:
    vector<vector<int>> colorBorder(vector<vector<int>>& grid, int r0, int c0, int color) {
        this->rows = grid.size();
        this->cols = grid[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));
        this->currColor = grid[r0][c0];
        if (currColor == color) return grid;
        dfs(r0, c0, color, grid);
        return grid;
    }

    void dfs(int row, int col, int color, vector<vector<int>>& grid) {
        visited[row][col] = true;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (!inArea(nextRow, nextCol)
                    || (grid[nextRow][nextCol] != currColor && !visited[nextRow][nextCol])) {
                grid[row][col] = color;
            } else if (!visited[nextRow][nextCol]){
                dfs(nextRow, nextCol, color, grid);
            }
        }
    }
};