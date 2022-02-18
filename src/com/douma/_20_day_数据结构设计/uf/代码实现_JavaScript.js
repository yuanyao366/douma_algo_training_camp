class UnionFind {
    constructor(capacity) {
        this.parent = new Array(capacity).fill(0)
        this.rank = new Array(capacity).fill(0)
        for (let i = 0; i < capacity; i++) {
            this.parent[i] = i
            this.rank[i] = 1
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
    }
}