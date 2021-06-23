class Solution {
private:
    int rows, cols;
    vector<vector<int>> dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int oldColor;

public:
    // DFS
    vector<vector<int>> floodFill1(vector<vector<int>>& image, int sr, int sc, int newColor) {
        this->oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }

        this->rows = image.size();
        this->cols = image[0].size();

        dfs(sr, sc, newColor, image);
        return image;
    }

    void dfs(int row, int col, int newColor, vector<vector<int>>& image) {
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || image[row][col] != oldColor) {
            return;
        }

        image[row][col] = newColor;

        for (vector<int> dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            dfs(nextRow, nextCol, newColor, image);
        }
    }

    // BFS
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        this->oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }

        int rows = image.size();
        int cols = image[0].size();

        queue<vector<int>> q;
        q.push({sr, sc});
        image[sr][sc] = newColor;

        while (!q.empty()) {
            vector<int> curr = q.front();
            q.pop();
            for (vector<int> dir : dirs) {
                int nextRow = curr[0] + dir[0];
                int nextCol = curr[1] + dir[1];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && image[nextRow][nextCol] == oldColor) {
                    q.push({nextRow, nextCol});
                    image[nextRow][nextCol] = newColor;
                }
            }
        }
        return image;
    }
};