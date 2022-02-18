# 抖码算法，让算法学习变得简单有趣
# 作者：菲菲

if __name__ == '__main__':
    data = str(input()).split(" ")
    x, n = int(data[0]), int(data[1])
    w, p, v = [0] * n, [0] * n, [0] * n
    for i in range(n):
        data = str(input()).split(" ")
        w[i], p[i], v[i] = int(data[0]), int(data[1]), int(data[2])

    # 把多重背包问题，转化成 0-1 背包问题
    # 比如，有 2 件价值为 5，重量为 2 的同一物品，
    # 我们就可以分为物品 a和物品 b，a 和 b 的价值都为 5，重量都为 2，
    # 但我们把它们视作不同的物品。
    new_n = sum(p)
    new_w, new_v, index = [0] * new_n, [0] * new_n, 0
    for i in range(n):
        for j in range(p[i]):
            new_w[index], new_v[index], index = w[i], v[i], index + 1

    # 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
    dp = [0] * (x + 1)

    # 2. 状态初始化

    # 3. 状态转移
    for i in range(new_n):
        for c in range(x, new_w[i] - 1, -1):
            dp[c] = max(dp[c], new_v[i] + dp[c - new_w[i]])

    print(dp[x])
