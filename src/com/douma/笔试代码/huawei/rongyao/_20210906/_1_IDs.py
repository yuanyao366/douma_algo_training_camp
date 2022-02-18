# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

mod = 1000000007
while True:
    data = str(input()).split(" ")
    N, L = int(data[0]), int(data[1])
    if N == 0 and L == 0:
        break

    res = 0
    # 时间复杂度：O(LlogN)
    for l in range(1, L + 1):
        # 求 cnt = l^N
        cnt, a, b = 1, l, N
        while a > 0:
            if a % 2 == 1:
                cnt = (cnt * b) % mod
            a, b = a // 2, (b * b) % mod
        res = (res + cnt) % mod

    print(res)