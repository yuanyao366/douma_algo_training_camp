class UnionFind {
    constructor(capacity) {
        this.parent = new Array(capacity).fill(0)
        this.rank = new Array(capacity).fill(0)
        this.circles = 0
        for (let i = 0; i < capacity; i++) {
            this.parent[i] = i
            this.rank[i] = 1
            this.circles++
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
        this.circles--
    }

    getCircles() {
        return this.circles
    }
}

/**
 * @param {number[][]} isConnected
 * @return {number}
 */
var findCircleNum = function(isConnected) {
    const rows = isConnected.length
    const cols = isConnected[0].length
    const uf = new UnionFind(rows)
    for (let i = 0; i < rows; i++) {
        for (let j = i + 1; j < cols; j++) {
            if (isConnected[i][j] == 1) uf.unionElement(i, j)
        }
    }
    return uf.getCircles();
};