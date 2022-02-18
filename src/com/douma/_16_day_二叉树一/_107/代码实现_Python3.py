# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def levelOrderBottom1(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []

        res = []
        queue = deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            level_nodes = []
            for i in range(size):
                curr = queue.popleft()
                level_nodes.append(curr.val)
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
            res.append(level_nodes)
        return res[::-1]

    # 前序遍历
    def levelOrderBottom(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []
        res = list()
        self.preorder(root, 0, res)
        return res[::-1]

    def preorder(self, node: TreeNode, curr_level: int,  res: List[int]) -> None:
        if not node:
            return
        if len(res) == curr_level:
            res.append([node.val])
        else:
            res[curr_level].append(node.val)

        self.preorder(node.left, curr_level + 1, res)
        self.preorder(node.right, curr_level + 1, res)