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
    def postorder1(self, root: 'Node') -> List[int]:
        if not root: return []

        res, stack = list(), list()
        stack.append(root)
        while len(stack):
            curr = stack.pop()
            res.append(curr.val)
            for node in curr.children:
                stack.append(node)
        res.reverse()
        return res

    # 递归
    def postorder(self, root: 'Node') -> List[int]:
        def dfs(node, res) -> None:
            if not node: return
            for curr in node.children:
                dfs(curr, res)
            res.append(node.val)

        res = list()
        dfs(root, res)
        return res