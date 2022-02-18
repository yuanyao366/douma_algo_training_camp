# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.sum = 0

    # 前序遍历
    def sumOfLeftLeaves1(self, root: TreeNode) -> int:
        self.preorder(root, root)
        return self.sum

    def preorder(self, node: TreeNode, parent: TreeNode) -> None:
        if not node:
            return
        if not node.left and not node.right and parent.left == node:
            self.sum += node.val
        self.preorder(node.left, node)
        self.preorder(node.right, node)

    # 后序遍历
    def sumOfLeftLeaves2(self, root: TreeNode) -> int:
        return self.postorder(root, root)

    def postorder(self, node: TreeNode, parent: TreeNode) -> int:
        if not node: return 0
        if not node.left and not node.right and parent.left == node:
            return node.val
        left_leaves_sum = self.postorder(node.left, node)
        right_leaves_sum = self.postorder(node.right, node)
        return left_leaves_sum + right_leaves_sum

    # BFS
    def sumOfLeftLeaves(self, root: TreeNode) -> int:
        if not root: return 0
        ans, queue = 0, deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            for i in range(size):
                curr = queue.popleft()
                if curr.left:
                    if self.isLeaveNode(curr.left):
                        ans += curr.left.val
                    else:
                        queue.append(curr.left)
                if curr.right and not self.isLeaveNode(curr.right):
                    queue.append(curr.right)
        return ans

    def isLeaveNode(self, node: TreeNode) -> bool:
        return not node.left and not node.right