# 抖码算法，让算法学习变得简单有趣
# 作者：小莹

# 稳固积木
# 时间复杂度：O(nlogn)
# 空间复杂度：O(n)
if __name__ == '__main__':
    # 1. 处理输入数据
    line = str(input()).split(" ")
    n, x, k = int(line[0]), int(line[1]), int(line[2])
    data, weights = str(input()).split(" "), [0] * n
    for i in range(n):
        weights[i] = int(data[i])

    # 2. 对积木重量升序排列
    # 排序的目的：可以快速的得到每个积木重量相差最小的积木
    weights.sort()

    # 3. 找到所有相邻积木重量差大于 x 的间隔，并计算需要多少个任意重量积木可以填平这个间隔
    """
    比如，n = 10, x = 2, k = 5
    积木重量：1, 1, 4, 9, 13, 15, 17, 19, 22, 29
    那么相邻积木重量差大于 x 的间隔是：1~4, 4~9, 9~13, 19~22, 22~29
    每个间隔需要的任意重量积木分别是：1, 2, 1, 1, 3
    """
    needed_bricks = []
    for i in range(1, n):
        diff = weights[i] - weights[i - 1]
        if diff > x:
            # 要分 diff 是不是 x 的整数倍
            # 比如间隔 9 ~ 13 的 diff 值是 4， 是 x = 2 的 2 倍
            # 这个时候只需要 1 个任意木头，就可以填平了
            if diff / x > diff // x:
                needed_bricks.append(diff // x)
            else:
                needed_bricks.append(diff // x - 1)

    """
    假如我们得到的 needed_bricks = [1, 2, 1, 1, 3]
    也就是说，到现在为止积木堆的最小数量是 len(needed_bricks) + 1
    但是，我们还有 k 个任意重量的积木，可以用来填平上面的间隔
    那么，现在的问题就变成了：使用 k 个任意重量的积木去填平尽可能多的间隔
    说白了，就是要 needed_bricks 中元素值尽可能多的为 0
    这里可以使用贪心：
        局部最优是尽可能多的填平小间隔，从而可以达到全局的尽可能多的填平间隔
    """
    # 先排序
    needed_bricks.sort()
    # 然后尽可能多的填平小间隔，并记录填平的间隔的数量 zeros
    zeros = 0
    for i in range(len(needed_bricks)):
        if needed_bricks[i] <= k:
            zeros += 1
            k -= needed_bricks[i]
        else:
            break

    # 最终的答案是：间隔的个数减去填平间隔的个数再 + 1
    print(len(needed_bricks) - zeros + 1)
