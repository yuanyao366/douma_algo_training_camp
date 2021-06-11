# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.ans = 0

    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        if not root: return 0
        self.maxDepth(root)
        return self.ans

    # DFS 后序遍历 递归
    def maxDepth(self, root: TreeNode) -> int:
        if not root: return 0

        leftMaxDepth = self.maxDepth(root.left)
        rightMaxDepth = self.maxDepth(root.right)

        self.ans = max(self.ans, leftMaxDepth + rightMaxDepth)

        return max(leftMaxDepth, rightMaxDepth) + 1