# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:
        idx_map = {val : i for i, val in enumerate(inorder)}
        root_index = len(postorder) - 1

        def buildTree(left: int, right: int) -> TreeNode:
            if left > right: return None

            nonlocal root_index
            root_val = postorder[root_index]
            root_index -= 1
            root, mid = TreeNode(root_val), idx_map[root_val]

            root.right = buildTree(mid + 1, right)
            root.left = buildTree(left, mid - 1)

            return root

        return buildTree(0, len(inorder) - 1)