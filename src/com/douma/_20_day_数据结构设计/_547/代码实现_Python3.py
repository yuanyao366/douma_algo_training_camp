class UnionFind:
    def __init__(self, capacity):
        self.parent = [0] * capacity
        self.rank = [0] * capacity
        self.circles = 0
        for i in range(capacity):
            self.parent[i] = i
            self.rank[i] = 1
            self.circles += 1

    def find(self, p):
        if p < 0 or p >= len(self.parent):
            raise Exception("p 超出了范围")
        while p != self.parent[p]:
            self.parent[p] = self.parent[self.parent[p]]
            p = self.parent[p]
        return p

    def is_connected(self, p, q):
        return self.find(p) == self.find(q)

    def union_element(self, p, q):
        p_root, q_root = self.find(p), self.find(q)
        if p_root == q_root:
            return
        if self.rank[p_root] < self.rank[q_root]:
            self.parent[p_root] = q_root
        elif self.rank[p_root] > self.rank[q_root]:
            self.parent[q_root] = p_root
        else:
            self.parent[q_root] = p_root
            self.rank[p_root] += 1
        self.circles -= 1

    def get_circles(self):
        return self.circles

class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        rows, cols = len(isConnected), len(isConnected[0])
        uf = UnionFind(rows)
        for i in range(rows):
            for j in range(i + 1, cols):
                if isConnected[i][j] == 1:
                    uf.union_element(i, j)

        return uf.get_circles()