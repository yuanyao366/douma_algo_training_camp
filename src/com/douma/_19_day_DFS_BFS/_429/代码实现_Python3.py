from typing import List


"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    # 迭代 BFS
    def levelOrder1(self, root: 'Node') -> List[List[int]]:
        if not root: return []
        res, queue = [], deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            level_nodes = []
            for i in range(size):
                curr = queue.popleft()
                level_nodes.append(curr.val)
                for node in curr.children:
                    queue.append(node)
            res.append(level_nodes)

        return res

    # 迭代 DFS
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        def dfs(node, curr_level,  res) -> None:
            if not node:
                return
            if len(res) == curr_level:
                res.append([node.val])
            else:
                res[curr_level].append(node.val)
            for curr in node.children:
                dfs(curr, curr_level + 1, res)
        res = list()
        dfs(root, 0, res)
        return res