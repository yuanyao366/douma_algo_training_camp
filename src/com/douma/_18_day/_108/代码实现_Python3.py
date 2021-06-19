# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:

        def build_tree(nums: List[int], left: int, right: int) -> TreeNode:
            if left > right: return None

            mid = left + (right - left) // 2
            root = TreeNode(nums[mid])

            root.left = build_tree(nums, left, mid - 1)
            root.right = build_tree(nums, mid + 1, right)

            return root

        return build_tree(nums, 0, len(nums) - 1)