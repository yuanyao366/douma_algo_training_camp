# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # BFS
    def rightSideView(self, root: TreeNode) -> List[int]:
        if not root:
            return []
        res = []
        queue = deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            for i in range(size):
                curr = queue.popleft()
                if i == size - 1:
                    res.append(curr.val)
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
        return res

    # DFS
    def rightSideView(self, root: TreeNode) -> List[int]:
        res = []
        self.preorder(root, 0, res)
        return res

    def preorder(self, node: TreeNode, curr_level: int, res: List[int]) -> None:
        if not node:
            return
        if len(res) == curr_level:
            res.append(node.val)
        self.preorder(node.right, curr_level + 1, res)
        self.preorder(node.left, curr_level + 1, res)