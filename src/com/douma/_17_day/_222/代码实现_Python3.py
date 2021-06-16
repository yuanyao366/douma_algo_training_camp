# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # DFS
    def countNodes1(self, root: TreeNode) -> int:
        if not root: return 0

        left_nodes_cnt = self.countNodes1(root.left)
        right_nodes_cnt = self.countNodes1(root.right)

        return left_nodes_cnt + right_nodes_cnt + 1

    # 二分
    def countNodes(self, root: TreeNode) -> int:
        if not root: return 0

        def exists(root: TreeNode, level: int, k: int) -> bool:
            mask, node = 1 << (level - 1), root
            while node and mask > 0:
                if mask & k:
                    node = node.right
                else:
                    node = node.left
                mask >>= 1
            return node is not None

        level, curr = 0, root
        while curr.left:
            level, curr = level + 1, curr.left

        # 完全二叉树的节点数的范围是：[2^level, 2^(level + 1) - 1]
        low = (1 << level)
        high = (1 << (level + 1)) - 1
        while low < high:
            mid = low + (high - low + 1) // 2
            if exists(root, level, mid):
                low = mid
            else:
                high = mid -1
        return low