# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.x = self.y = self.prev = None

    def recoverTree(self, root: TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        def dfs(node) -> None:
            if not node: return
            dfs(node.left)
            if self.prev and node.val < self.prev.val:
                self.y = node
                if not self.x:
                    self.x = self.prev
                else:
                    return
            self.prev = node
            dfs(node.right)

        dfs(root)
        self.x.val, self.y.val = self.y.val, self.x.val