# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 时间复杂度：O(nlogm), n 是数组的大小，m 最大值为 10^9
if __name__ == '__main__':
    data = str(input()).split(" ")
    n, k = int(data[0]), int(data[1])

    if n == 1 and k == n - 1:
        print(0)
        exit(0)

    data = str(input()).split(" ")
    b, max_abs_diff = [0] * n, 0
    for i in range(n):
        b[i] = int(data[i])
        if i >= 1 and abs(b[i] - b[i - 1] > max_abs_diff):
            max_abs_diff = [i] - b[i - 1]


    def get_least_modify_times(abs_diff):
        cnt, i = 0, 1
        while i < n:
            if abs(b[i] - b[i - 1]) > abs_diff:
                """
                如果出现差绝对值大于 mid 的话，就尝试修改一次
                修改的话，尽可能修改最少的次数：
                     如果后一个元素减去前一个元素的绝对值小于 2 倍的 mid
                     那么就修改了当前元素值，就没必要修改后面的一个元素，这里有点贪心的意思
                """
                if i < n - 1 and abs(b[i + 1] - b[i - 1]) <= 2 * abs_diff:
                    i += 2
                else:
                    i += 1
            else:
                i += 1
        return cnt


    low, high = 0, max_abs_diff
    while low < high:
        mid = low + (high - low) // 2
        # 计算要使得最大差绝对值 = mid 的话，最少需要修改的次数
        cnt = get_least_modify_times(mid)

        """
        如果使得最大差绝对值 = mid，需要修改的最小次数大于 k 的话
        说明在最多修改 k 次的情况下，最小的【最大差绝对值】肯定要比 mid 大
        所以需要去右半区查找大一点的值
        """
        if cnt > k:
            low = mid + 1
        else:
            high = mid

    print(low)
