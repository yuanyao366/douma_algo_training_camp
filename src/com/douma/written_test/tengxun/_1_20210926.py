# 抖码算法，让算法学习变得简单有趣
# 微信公众号：抖码课堂
# 作者：老汤


# 时间复杂度是：O(logn)，因为树的高度平均为 logn，n 的大小最大为 10^5
def is_ancestor(i, j, parents):
    while j != 1:
        if i == j:
            return True
        j = parents[j]
    return False


# 详见 leetcode 367. 有效的完全平方数：https://leetcode-cn.com/problems/valid-perfect-square/
# 时间复杂度是：O(logn)，因为这道题目的节点的值最大为 100，所以这里的 num 最大为 100 * 100 = 10000
# log10000 约等于 13 ，这个性能是可以接受的
def is_square_number(num):
    if num < 2:
        return True

    left, right = 2, num // 2

    while left <= right:
        x = left + (right - left) // 2
        guess_squared = x * x
        if guess_squared == num:
            return True
        if guess_squared > num:
            right = x - 1
        else:
            left = x + 1

    return False


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
    parents[1] = 1
    for i in range(n - 1):
        parents[i + 2] = int(nodeParents[i])

    res = 0
    for i in range(1, n + 1):
        for j in range(i + 1, n + 1):
            if is_ancestor(i, j, parents) and is_square_number(data[i] * data[j]):
                res += 1

    print(res)
