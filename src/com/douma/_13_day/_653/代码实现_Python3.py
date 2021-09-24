# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 方法一：二分查找
    def findTarget1(self, root: TreeNode, k: int) -> bool:
        nums = []
        def inOrder(node):
            if not node:
                return
            inOrder(node.left)
            nums.append(node.val)
            inOrder(node.right)

        inOrder(root)

        left, right = 0, len(nums) - 1
        while left < right:
            lr_sum = nums[left] + nums[right]
            if lr_sum == k:
                return True
            elif lr_sum < k:
                left += 1
            else:
                right -= 1
        return False

    # 方法二：哈希查找
    def findTarget(self, root: TreeNode, k: int) -> bool:
        s = set()
        def find(node):
            if not node:
                return False
            if (k - node.val) in s:
                return True
            s.add(node.val)
            return find(node.left) or find(node.right)
        return find(root)