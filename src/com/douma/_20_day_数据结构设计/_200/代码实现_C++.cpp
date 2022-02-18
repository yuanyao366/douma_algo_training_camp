class UnionFind {
private:
    vector<int> parent;
    vector<int> rank;
    int count = 0;

public:

    UnionFind(vector<vector<char>>& grid) {
        int rows = grid.size();
        int cols = grid[0].size();

        parent = vector<int>(rows * cols);
        rank = vector<int>(rows * cols);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * cols + j] = i * cols + j;
                    rank[i * cols + j] = 1;
                    count++;
                }  
            }
        }
    }

    int find(int p) {
        if (p < 0 || p >= parent.size()) {
            throw "p 超出了范围";
        }
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    bool isConnected(int p, int q) {
        return find(p) == find(q);
    }
    
    void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

        count--;
    }

    int getCount() {
        return count;
    } 

};

class Solution {
public:

    // 并查集
    int numIslands(vector<vector<char>>& grid) {
        this->rows = grid.size();
        this->cols = grid[0].size();

        UnionFind* uf = new UnionFind(grid);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (vector<int> dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == '1') {
                            uf->unionElement(i * cols + j, nextRow * cols + nextCol);
                        }
                    }
                }
            }
        }

        return uf->getCount();
    }
};