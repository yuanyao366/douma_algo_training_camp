# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
        self.res = 2 ** 31 - 1

    # BFS
    def minDepth1(self, root: TreeNode) -> int:
        if not root: return 0

        res, queue = 0, deque()
        queue.append(root)
        while len(queue):
            size = len(queue)
            res += 1
            for i in range(size):
                curr = queue.popleft()
                if not curr.left and not curr.right:
                    return res
                if curr.left:
                    queue.append(curr.left)
                if curr.right:
                    queue.append(curr.right)
        return res

    # DFS 前序遍历
    def minDepth2(self, root: TreeNode) -> int:
        if not root: return 0

        res, stack = 2 ** 31 - 1, list()
        stack.append([root, 1])
        while len(stack):
            curr_node, curr_level = stack.pop()

            if not curr_node.left and not curr_node.right:
                res = min(res, curr_level)

            if curr_node.right:
                stack.append([curr_node.right, curr_level + 1])
            if curr_node.left:
                stack.append([curr_node.left, curr_level + 1])

        return res

    # DFS 前序遍历 递归
    def minDepth3(self, root: TreeNode) -> int:
        if not root: return 0
        self.preorder(root, 1)
        return self.res

    def preorder(self, node: TreeNode, curr_level: int) -> None:
        if not node: return

        if not node.left and not node.right:
            self.res = min(self.res, curr_level)

        self.preorder(node.left, curr_level + 1)
        self.preorder(node.right, curr_level + 1)


    # DFS 后序遍历 递归
    def minDepth(self, root: TreeNode) -> int:
        if not root: return 0

        leftMinDepth = self.minDepth(root.left)
        rightMinDepth = self.minDepth(root.right)

        if not root.left:
            return rightMinDepth + 1
        elif not root.right:
            return leftMinDepth + 1
        return min(leftMinDepth, rightMinDepth) + 1