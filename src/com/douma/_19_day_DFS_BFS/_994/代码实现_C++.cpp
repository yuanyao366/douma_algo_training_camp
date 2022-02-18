class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.size();
        int cols = grid[0].size();

        unordered_map<int, int> levelsMap;

        queue<int> q;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    int index = row * cols + col;
                    q.push(index);
                    levelsMap[index] = 0;
                }
            }
        }

        int ans = 0;
        while (!q.empty()) {
            int currIndex = q.front();
            q.pop();
            int row = currIndex / cols;
            int col = currIndex % cols;
            for (vector<int> dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && grid[nextRow][nextCol] == 1) {
                    grid[nextRow][nextCol] = 2;

                    int index = nextRow * cols + nextCol;
                    q.push(index);

                    // 更新所属层信息
                    int level = levelsMap[currIndex] + 1;
                    levelsMap[index] = level;

                    ans = level;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) return -1;
            }
        }

        return ans;
    }
};