# 抖码算法，让算法学习变得简单有趣
# 作者：菲菲

if __name__ == '__main__':
    data = str(input()).split(" ")
    x, m = int(data[0]), int(data[1])
    data = str(input()).split(" ")
    prices = [0] * m
    for i in range(m):
        prices[i] = int(data[i])

    res = 0


    def dfs(index, target):
        if target == 0:
            global res
            res += 1
            return
        for i in range(index, m):
            if target < prices[i]:
                continue
            dfs(i + 1, target - prices[i])


    dfs(0, x)
    print(res)
