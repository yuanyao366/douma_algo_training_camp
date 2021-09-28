func findCircleNum(isConnected [][]int) int {
    var rows, cols = len(isConnected), len(isConnected[0])
    var uf = newUnionFind(rows)

    for i := 0; i < rows; i++ {
        for j := i + 1; j < cols; j++ {
            if isConnected[i][j] == 1 {
                uf.unionElement(i, j)
            }
        }
    }

    return uf.getCircles()
}


type UnionFind struct {
    parent []int
    rank []int
    circles int
}

func newUnionFind(capacity int) UnionFind {
    var uf = UnionFind{
        parent:make([]int, capacity),
        rank:make([]int, capacity),
    }

    for i := 0; i < capacity; i++ {
        uf.parent[i] = i
        uf.rank[i] = 1
        uf.circles++
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
    this.circles--
}

func (this *UnionFind) getCircles() int {
    return this.circles
}