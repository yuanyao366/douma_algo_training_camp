# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

if __name__ == '__main__':
    data = str(input()).split(" ")
    n, total_sum = 0, 0
    coins = [0] * n
    for i in range(n):
        coins[i] = int(data[i])
        total_sum += coins[i]
    k = int(input())

    # 通过滑动窗口维护数组中连续的 n - k 个元素的最小和
    window_min_sum, window_sum = float("inf"), 0
    left = right = 0
    while right < n:
        window_sum += coins[right]

        if right - left + 1 == n - k:
            window_min_sum = min(window_min_sum, window_sum)
            window_sum -= coins[left]
            left += 1

        right += 1

    # 将总和减去中间 n - k 个元素的最小和，得到的就是从两端可以拿到的最大和
    print(total_sum - window_min_sum)