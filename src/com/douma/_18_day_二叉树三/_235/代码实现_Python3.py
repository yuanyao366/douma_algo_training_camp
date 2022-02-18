# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # 1. 递归
    def lowestCommonAncestor1(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root or root == p or root == q:
            return root
        left = self.lowestCommonAncestor1(root.left, p, q)
        right = self.lowestCommonAncestor1(root.right, p, q)
        if not left:
            return right
        if not right:
            return left
        return root

    # 2. 迭代
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root or root == p or root == q:
            return root
        ancestor = root
        while ancestor:
            if p.val > ancestor.val and q.val > ancestor.val:
                ancestor = ancestor.right
            elif p.val < ancestor.val and q.val < ancestor.val:
                ancestor = ancestor.left
            else:
                break
        return ancestor