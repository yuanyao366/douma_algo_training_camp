# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 1. 迭代
    def insertIntoBST1(self, root: TreeNode, val: int) -> TreeNode:
        if not root: return TreeNode(val)
        curr = root
        while curr:
            if val < curr.val and not curr.left:
                curr.left = TreeNode(val)
                return root
            elif val > curr.val and not curr.right:
                curr.right = TreeNode(val)
                return root

            if val < curr.val:
                curr = curr.left
            else:
                curr = curr.right
        return root

    # 2. 递归
    def insertIntoBST(self, root: TreeNode, val: int) -> TreeNode:
        if not root: return TreeNode(val)
        if val < root.val:
            root.left = self.insertIntoBST(root.left, val)
        else:
            root.right = self.insertIntoBST(root.right, val)
        return root