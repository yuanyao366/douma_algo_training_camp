# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def __init__(self):
        self.parents = {}
        self.visited = set()

    def lowestCommonAncestor1(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        self.dfs(root)

        while p:
            self.visited.add(p)
            p = self.parents.get(p.val, None)

        while q:
            if q in self.visited:
                return q
            q = self.parents[q.val]

        return None

    def dfs(self, node: 'TreeNode') -> None:
        if not node: return None
        if node.left:
            self.parents[node.left.val] = node
        if node.right:
            self.parents[node.right.val] = node

        self.dfs(node.left)
        self.dfs(node.right)

        # DFS 后序遍历
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root: return None
        if root == p or root == q:
            return root

        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)

        if not left: return right
        if not right: return left
        return root