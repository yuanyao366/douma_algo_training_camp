# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def pruning(self, root):

        def is_leaf(node):
            if not node:
                return False
            return not node.left and not node.right

        # 修剪以 node 为根节点的树中的叶子节点，并返回修剪后的树的根节点
        def trim_leaf_nodes(node):
            # 如果树是空的，或者只有一个节点，那么直接返回空树
            if not node or is_leaf(node):
                return None

            # 如果当前节点 node 的左右孩子有一个是叶子节点
            # 那么删除这个节点，也就是将这个节点从树中脱离掉，并返回空树
            if is_leaf(node.left) or is_leaf(node.right):
                return None

            # 递归的去左子树、右子树中修剪叶子节点
            node.left = trim_leaf_nodes(node.left)
            node.right = trim_leaf_nodes(node.right)

            return node

        return trim_leaf_nodes(root)
