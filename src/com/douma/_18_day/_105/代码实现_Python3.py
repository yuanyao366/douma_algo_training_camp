# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 迭代
    def buildTree1(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        stack, root = [], TreeNode(preorder[0])
        stack.append(root)
        inorder_index = 0
        for i in range(1, len(inorder)):
            child_val, parent_node = preorder[i], stack[-1]
            if parent_node.val != inorder[inorder_index]:
                parent_node.left = TreeNode(child_val)
                stack.append(parent_node.left)
            else:
                while len(stack) and stack[-1].val == inorder[inorder_index]:
                    parent_node = stack.pop()
                    inorder_index += 1
                parent_node.right = TreeNode(child_val)
                stack.append(parent_node.right)
        return root

    # 递归
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        idx_map = {val : i for i, val in enumerate(inorder)}
        root_index = 0

        def buildTree(left: int, right: int) -> TreeNode:
            if left > right: return None

            nonlocal root_index
            root_val = preorder[root_index]
            root_index += 1
            root, mid = TreeNode(root_val), idx_map[root_val]

            root.left = buildTree(left, mid - 1)
            root.right = buildTree(mid + 1, right)

            return root

        return buildTree(0, len(inorder) - 1)