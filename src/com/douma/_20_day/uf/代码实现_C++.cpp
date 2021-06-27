class UnionFind {
private:
    vector<int> parent;
    vector<int> rank;

public:
    UnionFind(int capacity) {
        parent = vector<int>(capacity);
        rank = vector<int>(capacity);

        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
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
    }

};