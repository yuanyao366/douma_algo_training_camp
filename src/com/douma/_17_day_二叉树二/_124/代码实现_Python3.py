# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.max_sum = -2**31

    def maxPathSum(self, root: TreeNode) -> int:
        def max_gain(node: TreeNode) -> int:
            if not node: return 0

            left_gain = max(max_gain(node.left), 0)
            right_gain = max(max_gain(node.right), 0)

            self.max_sum = max(self.max_sum, left_gain + right_gain + node.val)
            return max(left_gain, right_gain) + node.val

        max_gain(root)
        return self.max_sum