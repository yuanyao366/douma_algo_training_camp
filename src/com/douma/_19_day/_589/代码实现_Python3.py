from typing import List


"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    # 迭代
    def preorder1(self, root: 'Node') -> List[int]:
        if not root:
            return []
        res, stack = list(), list()
        stack.append(root)
        while len(stack):
            curr = stack.pop()
            res.append(curr.val)
            for i in range(len(curr.children) - 1, -1, -1):
                stack.append(curr.children[i])
        return res

    # 递归
    def preorder(self, root: 'Node') -> List[int]:
        def dfs(node, res) -> None:
            if not node: return
            res.append(node.val)
            for i in range(len(node.children)):
                dfs(node.children[i], res)
        res = list()
        dfs(root, res)
        return res