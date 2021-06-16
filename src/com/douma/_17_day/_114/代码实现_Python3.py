# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 前序遍历 + 串接节点
    def flatten1(self, root: TreeNode) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        res, curr = list(), root
        self.dfs(root, res)
        for i in range(1, len(res)):
            curr.left, curr.right = None, res[i]
            curr = curr.right

    def dfs(self, node: TreeNode, res: List[int]) -> None:
        if not node: return
        res.append(node)
        self.dfs(node.left, res)
        self.dfs(node.right, res)

    # 边遍历边串接
    def flatten2(self, root: TreeNode) -> None:
        if not root: return
        stack, prev = [], None
        stack.append(root)
        while len(stack):
            curr = stack.pop()
            if prev:
                prev.left, prev.right = None, curr
            if curr.right: stack.append(curr.right)
            if curr.left: stack.append(curr.left)
            prev = curr

    # 寻找前继节点
    def flatten(self, root: TreeNode) -> None:
        if not root: return
        curr = root
        while curr:
            if curr.left:
                left = pre = curr.left
                while pre.right: pre = pre.right
                pre.right = curr.right
                curr.left, curr.right = None, left
            curr = curr.right