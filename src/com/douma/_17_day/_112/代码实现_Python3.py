# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def hasPathSum(self, root: TreeNode, targetSum: int) -> bool:
        if not root: return False
        if not root.left and not root.right:
            return targetSum - root.val == 0
        left_has_path_sum = self.hasPathSum(root.left, targetSum - root.val)
        if left_has_path_sum: return True
        right_has_path_sum = self.hasPathSum(root.right, targetSum - root.val)
        return left_has_path_sum or right_has_path_sum