# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        def dfs(node: TreeNode, path: str, res: List[str]) -> None:
            if not node: return
            if not node.left and not node.right:
                res.append(path + str(node.val))
                return
            dfs(node.left, path + str(node.val) + "->", res)
            dfs(node.right, path + str(node.val) + "->", res)

        res = list()
        dfs(root, "", res)
        return res