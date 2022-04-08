# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

"""
力扣 98. 验证二叉搜索树
    给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    假设一个二叉搜索树具有如下特征：
        1.节点的左子树只包含小于当前节点的数。
        2.节点的右子树只包含大于当前节点的数。
        3. 所有左子树和右子树自身必须也是二叉搜索树。

         输入:
            2
           / \
          1   3
        输出: true

        输入:
            5
           / \
          1   4
             / \
            3   6
        输出: false
"""
"""
    ACM 模式输入描述:
        第一行表示所有的节点

    输入:
        5,1,4,null,null,3,6

    说明：
        (1) 索引为 0 的节点 5 是根节点
        (2) 索引为 0 的节点的左节点是：2 * 0 + 1 = 1，也就是索引为 1 的节点
        (3) 索引为 0 的节点的右节点是：2 * 0 + 2 = 2，也就是索引为 2 的节点
        (2) 索引为 1 的节点的左节点是：2 * 1 + 1 = 3，也就是索引为 3 的节点
        (3) 索引为 1 的节点的右节点是：2 * 1 + 2 = 4，也就是索引为 4 的节点
"""


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


# 2. 后序遍历
def isValidBST(root: TreeNode) -> bool:

    def isValidBST(node, lower = float('-inf'), upper = float('inf')) -> bool:
        if not node: return True
        if node.val <= lower or node.val >= upper:
            return False
        return isValidBST(node.left, lower, node.val) and isValidBST(node.right, node.val, upper)

    return isValidBST(root)


values = str(input()).split(",")
n = len(values)

# 1. 构造一棵树
# 记录每个节点
nodes = [None] * n
for i in range(n):
    val = values[i]
    if "null" != val:
        nodes[i] = TreeNode(int(val))

i = 0
while i * 2 + 2 < n:
    if nodes[i]:
        nodes[i].left = nodes[2 * i + 1]
        nodes[i].right = nodes[2 * i + 2]
    i += 1


# 2. 拿到树的根节点
root = nodes[0]

# 3. 判断这棵树是否是二叉查找树
res = isValidBST(root)

# 4. 打印结果
print(res)
