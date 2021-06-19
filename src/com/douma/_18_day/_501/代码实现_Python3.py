# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.count = 0
        self.max_count = 0
        self.prev_num = 0
        self.ans = []

    def findMode(self, root: TreeNode) -> List[int]:
        def dfs(node) -> None:
            if not node: return
            dfs(node.left)
            self.update(node.val)
            dfs(node.right)
        dfs(root)
        return self.ans

    def update(self, val: int) -> None:
        if val == self.prev_num:
            self.count += 1
        else:
            self.prev_num = val
            self.count = 1

        if self.count == self.max_count:
            self.ans.append(val)
        elif self.count > self.max_count:
            self.ans = [val]
            self.max_count = self.count