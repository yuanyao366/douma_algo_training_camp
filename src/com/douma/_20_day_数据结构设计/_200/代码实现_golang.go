
func numIslands(grid [][]byte) int {
    var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
    var rows, cols = len(grid), len(grid[0])

    var uf = newUnionFind(grid)
    for i := 0; i < rows; i++ {
        for j := 0; j < cols; j++ {
            if grid[i][j] == '1' {
                grid[i][j] = '0'
                for _, dir := range dirs {
                    var nextRow = i + dir[0]
                    var nextCol = j + dir[1]
                    if nextRow >= 0 && nextRow < rows &&
                            nextCol >= 0 && nextCol < cols &&
                            grid[nextRow][nextCol] == '1' {
                        uf.unionElement(i * cols + j, nextRow * cols + nextCol)
                    }
                }
            }
        }
    }

    return uf.getCount()
}


type UnionFind struct {
    parent []int
    rank []int
    count int
}

func newUnionFind(grid [][]byte) UnionFind {
    var rows, cols = len(grid), len(grid[0])

    var uf = UnionFind{
        parent:make([]int, rows * cols),
        rank:make([]int, rows * cols),
        count:0,
    }

    // bug 修复：i 应该是小于 rows
    for i := 0; i < rows; i++ {
        for j := 0; j < cols; j++ {
            if grid[i][j] == '1' {
                uf.parent[i * cols + j] = i * cols + j
                uf.rank[i * cols + j] = 1
                uf.count++
            }
        }
    }

    return uf
}

func (this *UnionFind) find(p int) int {
    if p < 0 || p >= len(this.parent) {
        panic("p 超出了范围")
    }

    for p != this.parent[p] {
        // 路径压缩
        this.parent[p] = this.parent[this.parent[p]]
        p = this.parent[p]
    }

    return p
}

func (this *UnionFind) isConnected(p int, q int) bool {
    return this.find(p) == this.find(q)
}

func (this *UnionFind) unionElement(p int, q int) {
    var pRoot, qRoot = this.find(p), this.find(q)
    if pRoot == qRoot {
        return
    }

    if this.rank[pRoot] < this.rank[qRoot] {
        this.parent[pRoot] = qRoot
    } else if this.rank[pRoot] > this.rank[qRoot] {
        this.parent[qRoot] = pRoot
    } else {
        this.parent[qRoot] = pRoot
        this.rank[pRoot] += 1
    }
    this.count--
}

func (this *UnionFind) getCount() int {
    return this.count
}