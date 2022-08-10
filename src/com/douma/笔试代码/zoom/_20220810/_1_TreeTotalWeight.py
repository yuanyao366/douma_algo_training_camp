# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


class TreeNode:
    def __init__(self, left=None, right=None, is_red=False):
        self.left = left
        self.right = right
        # true 表示红节点，false 表示蓝色节点
        self.is_red = is_red
        # self.red_num：用于记录到这个节点为止，路径上红色节点的个数
        # self.blue_num：用于记录到这个节点为止，路径上蓝色节点的个数
        if is_red:
            self.red_num, self.blue_num = 1, 0
        else:
            self.red_num, self.blue_num = 0, 1

    def get_weight(self):
        return abs(self.red_num - self.blue_num)

"""
* 求树的权值
*
* 告诉你一棵树的节点分为蓝色和红色，一个节点的权值定义为：
* 从根节点到该节点路径上的红蓝节点个数之差的绝对值
*
* 求整棵树的权值之和
"""
def total_weight(root):
    res = 0
    if root is None:
        return res

    res += root.get_weight()

    def dfs(node):
        if node is None:
            return

        nonlocal res
        if node.left:
            node.left.red_num += node.red_num
            node.left.blue_num += node.blue_num
            res += node.left.get_weight()
            dfs(node.left)
        if node.right:
            node.right.red_num += node.red_num
            node.right.blue_num += node.blue_num
            res += node.right.get_weight()
            dfs(node.right)

    dfs(root)
    return res


"""
构造一颗下面的树
                    红(1)
                  /    \
                蓝(0)   红(2)
              /   \
           红(1)  蓝(1)
"""
root = TreeNode(is_red=True)

node1 = TreeNode(is_red=False)
root.left = node1

node2 = TreeNode(is_red=True)
root.right = node2

node3 = TreeNode(is_red=True)
node1.left = node3

node4 = TreeNode(is_red=False)
node1.right = node4

print(total_weight(root))