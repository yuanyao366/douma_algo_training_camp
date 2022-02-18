# 抖码算法，让算法学习变得简单有趣
# 作者：小莹


def sort_min_num(s):
    """
    将字符串排序成最小整数，且开头不能有 0
    比如:
        字符串 1010 排序后就是 1001
        字符串 0100 排序后就是 1000
        字符串 9128 排序后就是 1289
    """
    non_zeros = []
    for c in s:
        if c != '0':
            non_zeros.append(c)

    if len(non_zeros) == len(s):
        res = list(s)
        res.sort()
        return "".join(res)
    elif len(non_zeros) == 0:
        return s

    non_zeros.sort()
    res = [non_zeros[0]]
    for _ in range(len(s) - len(non_zeros)):
        res.append('0')

    for i in range(1, len(non_zeros)):
        res.append(non_zeros[i])

    return "".join(res)


# 火柴可以组成的最大最小数
# 动态规划
if __name__ == '__main__':
    """
    huocai_nums 中的 key 是火柴数目，value 这个需要这个火柴数目的所有的数字
    huocai_nums = {2: [1], 3: [7], 4: [4], 5: [2, 3, 5], 6: [0, 6, 9], 7: [8]}
        n:      2   3   4   5   6   7   8       9       10      11      12      13      14      15
        max:    1   7   11  71  111 711 1111    7111    11111   71111   111111  711111  1111111 7111111
        min:    1   7   4   2   0   8   10      18      22      20      00      80      88      108
    """
    n = int(input())

    max_dp, min_dp = [""] * (n + 1), [""] * (n + 1)
    max_dp[2] = min_dp[2] = "1"
    max_dp[3] = min_dp[3] = "7"
    max_dp[4], min_dp[4] = "11", "4"
    max_dp[5], min_dp[5] = "71", "2"
    max_dp[6], min_dp[6] = "111", "0"
    max_dp[7], min_dp[7] = "711", "8"

    if n <= 7:
        print(max_dp[n])
    else:
        for i in range(8, n + 1):
            max_dp[i] = max_dp[i - 2] + max_dp[2]
            for j in range(i - 2, 1, -1):
                tmp = min_dp[i - j] + min_dp[j]
                tmp = sort_min_num(tmp)
                if min_dp[i] == "" or int(tmp) < int(min_dp[i]):
                    min_dp[i] = tmp
                if j <= i - j:
                    break

    print(0 if max_dp[n].startswith("0") else max_dp[n])
    print(0 if min_dp[n].startswith("0") else min_dp[n])
