from typing import List


class UnionFind:
    def __init__(self, grid):
        rows, cols = len(grid), len(grid[0])

        self.parent = [0] * rows * cols
        self.rank = [0] * rows * cols
        self.count = 0

        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == '1':
                    self.parent[i * cols + j] = i * cols + j
                    self.rank[i * cols + j] = 1
                    self.count += 1

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
        self.count -= 1

    def get_count(self):
        return self.count


class Solution:
    # 并查集
    def numIslands(self, grid: List[List[str]]) -> int:
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(grid), len(grid[0])

        def in_area(row, col) -> bool:
            return 0 <= row < rows and 0 <= col < cols

        uf = UnionFind(grid)
        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == '1':
                    grid[i][j] = '0'
                    for d in dirs:
                        next_row, next_col = i + d[0], j + d[1]
                        if in_area(next_row, next_col) and grid[next_row][next_col] == '1':
                            uf.union_element(i * cols + j, next_row * cols + next_col)
        return uf.get_count()