# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # BFS
    def widthOfBinaryTree(self, root: TreeNode) -> int:
        if not root: return 0
        max_width, queue = 0, deque()
        queue.append((root, 1))
        while len(queue):
            size, start_seq_no, end_seq_no = len(queue), 0, 0
            for i in range(size):
                (curr_node, seq_no) = queue.popleft()
                if i == 0: start_seq_no = seq_no
                if i == size - 1: end_seq_no = seq_no
                if curr_node.left:
                    queue.append((curr_node.left, 2 * seq_no))
                if curr_node.right:
                    queue.append((curr_node.right, 2 * seq_no + 1))
            max_width = max(max_width, end_seq_no - start_seq_no + 1)
        return max_width

    # DFS
    def widthOfBinaryTree2(self, root: TreeNode) -> int:
        return self.postorder(root, 0, 1, list(), list())

    def postorder(self, node: TreeNode, level: int, seq_no: int, start: List[int], end: List[int]) -> int:
        if not node: return 0

        if len(start) == level:
            start.append(seq_no)
            end.append(seq_no)
        else:
            end[level] = seq_no

        left_max_width = self.postorder(node.left, level + 1, 2 * seq_no, start, end)
        right_max_width = self.postorder(node.right, level + 1, 2 * seq_no + 1, start, end)

        curr_max_width = end[level] - start[level] + 1
        return max(curr_max_width, max(left_max_width, right_max_width))