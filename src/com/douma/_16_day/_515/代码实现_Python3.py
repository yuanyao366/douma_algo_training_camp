# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # BFS
    def largestValues1(self, root: TreeNode) -> List[int]:
        if not root: return []

        res, queue = [], deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            max_value = -2**31
            for i in range(size):
                curr = queue.popleft()
                max_value = max(max_value, curr.val)
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
            res.append(max_value)

        return res

    # DFS
    def largestValues(self, root: TreeNode) -> List[int]:
        res = list()
        self.preorder(root, 0, res)
        return res

    def preorder(self, node: TreeNode, curr_level: int, res: List[int]) -> None:
        if not node: return
        if len(res) == curr_level:
            res.append(node.val)
        else:
            max_value = max(res[curr_level], node.val)
            res[curr_level] = max_value

        self.preorder(node.left, curr_level + 1, res)
        self.preorder(node.right, curr_level + 1, res)