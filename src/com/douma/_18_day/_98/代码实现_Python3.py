# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 1. 中序遍历 (边遍历边验证顺序性)
    def isValidBST1(self, root: TreeNode) -> bool:
        is_bst, prev = True, None

        def dfs(node: TreeNode) -> None:
            if not node: return
            dfs(node.left)
            nonlocal prev
            if prev and node.val <= prev.val:
                nonlocal is_bst
                is_bst = False
            prev = node
            dfs(node.right)

        dfs(root)
        return is_bst

    # 2. 后序遍历
    def isValidBST(self, root: TreeNode) -> bool:

        def isValidBST(node, lower = float('-inf'), upper = float('inf')) -> bool:
            if not node: return True
            if node.val <= lower or node.val >= upper:
                return False
            return isValidBST(node.left, lower, node.val) and isValidBST(node.right, node.val, upper)

        return isValidBST(root)