# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pathSum(self, root: TreeNode, targetSum: int) -> List[List[int]]:
        res, path = list(), list()

        def dfs(node: TreeNode, targetSum: int) -> None:
            if not node: return
            path.append(node.val)
            targetSum -= node.val
            if not node.left and not node.right and targetSum == 0:
                res.append(path[:])
            dfs(node.left, targetSum)
            dfs(node.right, targetSum)
            path.pop()

        dfs(root, targetSum)
        return res