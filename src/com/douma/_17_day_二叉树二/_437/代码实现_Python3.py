# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # DFS 计算每个节点所有路径和
    def pathSum1(self, root: TreeNode, sum: int) -> int:

        def dfs(root, sumlist):
            if root is None:
                return 0

            sumlist = [num + root.val for num in sumlist]
            sumlist.append(root.val)

            count = 0
            for num in sumlist:
                if num == sum:
                    count += 1

            return count + dfs(root.left, sumlist) + dfs(root.right, sumlist)

        return dfs(root, [])


    # DFS(前序遍历) + 前缀和
    def pathSum(self, root: TreeNode, sum: int) -> int:

        def dfs(node, curr_sum, target_sum, prefix_sum_map) -> int:
            if not node: return 0
            curr_sum += node.val
            res = prefix_sum_map.get(curr_sum - target_sum, 0)
            prefix_sum_map[curr_sum] = prefix_sum_map.get(curr_sum, 0) + 1

            res += dfs(node.left, curr_sum, target_sum, prefix_sum_map)
            res += dfs(node.right, curr_sum, target_sum, prefix_sum_map)

            prefix_sum_map[curr_sum] = prefix_sum_map[curr_sum] - 1
            return res

        prefix_sum_map = {0:1}
        return dfs(root, 0, sum, prefix_sum_map)