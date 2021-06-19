# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def convertBST(self, root: TreeNode) -> TreeNode:
        curr_sum = 0

        def dfs(node) -> None:
            if not node: return
            dfs(node.right)
            nonlocal curr_sum
            curr_sum += node.val
            node.val = curr_sum
            dfs(node.left)

        dfs(root)
        return root