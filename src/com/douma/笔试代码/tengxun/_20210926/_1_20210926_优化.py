# 抖码算法，让算法学习变得简单有趣
# 微信公众号：抖码课堂
# 作者：小莹
import math


def put_ancestors(ancestor, child, ancestors):
    if ancestor in ancestors:
        ancestors[ancestor].append(child)
    else:
        children = [child]
        ancestors[ancestor] = children


# o(1)
def is_square_number(num):
    a = math.sqrt(num)
    b = int(a)
    return a == b


# issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4C16V
# 暴力解法
# 时间复杂度：O(n^2 * logn)
# 空间复杂度：O(1)
if __name__ == '__main__':
    # 第一行是节点的个数
    n = int(input())

    data, parents = [0] * (n + 1), [0] * (n + 1)
    # 第二行是每个节点的值
    nodes = str(input()).split(" ")
    for i in range(n):
        data[i + 1] = int(nodes[i])
    # 第三行是每个节点的父亲节点的索引
    nodeParents = str(input()).split(" ")
    parents[1] = 0  # 根节点没有父亲节点
    for i in range(n - 1):
        parents[i + 2] = int(nodeParents[i])

    # 用一个 Map 维护祖先及其所有子节点的关系
    # key 是祖先的 id，value 是这个祖先的所有孩子
    ancestors = dict()
    # 这里的时间复杂度：O(nlogn)，n 的大小最大为 10^5
    for i in range(2, n + 1):
        parent = parents[i]
        # 时间复杂度是：O(logn)，因为树的高度平均为 logn
        while parent != 0:
            put_ancestors(parent, i, ancestors)
            parent = parents[parent]

    res = 0
    # 总的时间复杂度：低于 O(n^2)
    # 因为树的高度会尽量保持在 O(logn)，也就是树会尽量的平衡
    # 平衡的树一般有一半左右的叶子节点，所以可以作为祖先的节点的个数是 n/2
    for ancestor, children in ancestors.items():
        # 根节点作为祖先的话，那么孩子有 n - 1 个
        # 根节点以下的节点作为祖先的话，孩子节点的个数成倍数的减少
        for child in children:
            # 优化为 O(1)
            if is_square_number(data[ancestor] * data[child]):
                res += 1

    print(res)
