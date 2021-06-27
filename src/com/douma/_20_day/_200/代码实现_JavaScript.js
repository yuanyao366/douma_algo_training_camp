class UnionFind {
    constructor(grid) {
        const rows = grid.length, cols = grid[0].length
        const capacity = rows * cols
        this.parent = new Array(capacity).fill(0)
        this.rank = new Array(capacity).fill(0)
        this.count = 0
        for (let i = 0; i < rows; i++) {
            for (let j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    this.parent[i * cols + j] = i * cols + j
                    this.rank[i * cols + j] = 1
                    this.count++
                }
            }
        }
    }

    find(p) {
        if (p < 0 && p >= this.parent.length) {
            throw new Error("p 超出了范围");
        }
        while (p != this.parent[p]) {
            this.parent[p] = this.parent[this.parent[p]]
            p = this.parent[p]
        }
        return p
    }

    isConnected(p, q) {
        return this.find(p) == this.find(q)
    }

    unionElement(p, q) {
        const pRoot = this.find(p)
        const qRoot = this.find(q)
        if (pRoot == qRoot) return

        if (this.rank[pRoot] < this.rank[qRoot]) {
            this.parent[pRoot] = qRoot
        } else if (this.rank[pRoot] > this.rank[qRoot]) {
            this.parent[qRoot] = pRoot
        } else {
            this.parent[qRoot] = pRoot
            this.rank[pRoot] += 1
        }

        this.count--
    }

    getCount() {
        return this.count
    }
};

// 并查集
var numIslands = function(grid) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    const rows = grid.length, cols = grid[0].length

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const uf = new UnionFind(grid)
    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (grid[row][col] == '1') {
                grid[row][col] == '0';
                for (let dir of dirs) {
                    const nextRow = row + dir[0]
                    const nextCol = col + dir[1]
                    if (inArea(nextRow, nextCol) && grid[nextRow][nextCol] == '1') {
                        uf.unionElement(row * cols + col, nextRow * cols + nextCol)
                    }
                }
            }
        }
    }

    return uf.getCount()
};