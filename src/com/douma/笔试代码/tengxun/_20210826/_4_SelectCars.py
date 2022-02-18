# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 时间复杂度：O(n)
# 空间复杂度：O(n) 或者 O(logn)
if __name__ == '__main__':
    # 1. 处理输入数据
    n = int(input())
    speed = [0] * n
    data = str(input()).split(" ")
    for i in range(n):
        speed[i] = int(data[i])

    # 2. 对 speed 升序排列
    speed.sort()

    # 3. 滑动窗口求符合条件的最多车辆数
    ans = left = right = 0
    while right < n:
        # 如果窗口内的最大值减去最小值超过了 10，那么缩减窗口大小
        while speed[right] - speed[left] > 10:
            left += 1

        # 如果窗口内最大值减去最小值不大于 10 ，那么：
        # 1. 更新符合条件的最大车辆数
        # 2. 扩大窗口
        ans = max(ans, right - left + 1)
        right += 1

    print(ans)
