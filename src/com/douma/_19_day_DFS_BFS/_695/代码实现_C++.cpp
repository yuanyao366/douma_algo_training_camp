class Solution {
private:
    vector<vector<int>> grid;
    int rows, cols;
    vector<vector<bool>> visited;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    bool inArea(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        this->grid = grid;
        this->rows = grid.size();
        this->cols = grid[0].size();
        this->visited = vector<vector<bool>>(rows, vector<bool>(cols));

        int ans = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int area = dfs(row, col);
                    ans = max(area, ans);
                }
            }
        }

        return ans;
    }

    int dfs(int row, int col) {
        if (!inArea(row, col) || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        int res = 0;
        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            res += dfs(nextRow, nextCol);
        }

        return 1 + res;
    }
};