from collections import deque
from typing import List


class Solution:
    # 深度优先遍历
    def isBipartite1(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        visited = [False] * n
        # -1 表示没有染颜色
        # 0 红色 1 蓝色
        colors = [-1] * n

        def dfs(v, color):
            visited[v], colors[v] = True, color
            for w in graph[v]:
                if not visited[w]:
                    # 如果 v 的颜色是 1，那么 w 的颜色就是 0
                    # 如果 v 的颜色是 0，那么 w 的颜色就是 1
                    if not dfs(w, 1 - color):
                        return False
                elif colors[w] == colors[v]:
                    # 如果相邻顶点的颜色一样的话，则不是二分图
                    return False
            return True

        for v in range(0, n):
            if not visited[v]:
                if not dfs(v, 0):
                    return False
        return True

    # 广度优先遍历
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)
        visited = [False] * n
        # -1 表示没有染颜色
        # 0 红色 1 蓝色
        colors = [-1] * n

        def bfs(v):
            q = deque()
            q.append(v)
            visited[v], colors[v] = True, 0
            while len(q):
                curr = q.popleft()
                for w in graph[curr]:
                    # 如果 w 没有遍历过，则需要染色
                    if not visited[w]:
                        q.append(w)
                        visited[w] = True
                        # 给顶点 w 染色，和 curr 的颜色不一样
                        colors[w] = 1 - colors[curr]
                    elif colors[w] == colors[curr]:
                        # 如果 w 被访问过，并且它的颜色和相邻点一样
                        # 那么可以判定不是二分图
                        return False
            return True

        for v in range(0, n):
            if not visited[v]:
                if not bfs(v):
                    return False
        return True
