# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 时间复杂度：O(n)，n 是字符串的长度
# 空间复杂度：O(1)
def largest_num(s, k):
    n, ans = len(s), -2**31
    # left 和 right 用于定义当前窗口的大小
    # 注意：这个窗口是从右往左滑动的，和我们之前见过的窗口有点不一样
    left = right = 0
    # windowNum 用于维护当前窗口的整数大小
    windowNum = 0
    while right < n:
        windowNum = windowNum * 10 + (ord(s[right]) - ord('0'))

        # 当前窗口的大小为 k，也就是当前窗口中有 k 个字符了
        if right - left + 1 == k:
            ans = max(ans, windowNum)
            # 缩小窗口，这这里需要维护当前窗口的数字大小
            # 也就是把当前窗口对应的数字的最高位减去掉即可
            windowNum -= (ord(s[left]) - ord('0')) * int(pow(10, k - 1))
            left += 1
        right += 1

    return ans


if __name__ == '__main__':
    print(largest_num("3215267", 3))