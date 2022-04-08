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
    第一行两个数 n, root，分别表示二叉树有 n 个节点，第 root 个节点时二叉树的根
    接下来共 n 行，第 i 行三个数 val_i, left_i, right_i，分别表示第 i 个节点的值 val，左儿子，右儿子
    值为 null 则表示空节点。

    输入:
        5 1
        5 2 3
        1 null null
        3 4 5
        4 null null
        6 null null
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


data = str(input()).split(" ")
n, root_index = int(data[0]), int(data[1])

# 1. 构造一棵树
# 记录每个节点
tree = [None] * n
# 记录每个节点的左右子节点的值
leaf = [[None] * 2 for _ in range(n)]
for i in range(n):
    s_arr = str(input()).split(" ")
    node = TreeNode(int(data[0]))
    leaf[i][0] = s_arr[1]
    leaf[i][1] = s_arr[2]
    tree[i] = node

for i in range(n):
    # 左子节点
    left = leaf[i][0]
    if "null" != left:
        # 这里减 1 是因为：节点值从 1 开始，而索引从 0 开始
        tree[i].left = tree[int(left) - 1]
    # 右子节点
    right = leaf[i][1]
    if "null" != right:
        tree[i].right = tree[int(right) - 1]

# 2. 拿到树的根节点
root = tree[root_index]

# 3. 判断这棵树是否是二叉查找树
res = isValidBST(root)

# 4. 打印结果
print(res)
