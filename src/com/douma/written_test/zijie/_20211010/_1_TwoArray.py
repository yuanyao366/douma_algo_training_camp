# 抖码算法，让算法学习变得简单有趣
# 作者：菲菲

# 两个数组中元素差的平方最接近 k^2
# 时间复杂度：O((m + n)logn)
if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        data = str(input()).split(" ")
        n, m, k = int(data[0]), int(data[1]), int(data[2])
        a, b = [0] * n, [0] * m
        data = str(input()).split(" ")
        for j in range(n):
            a[j] = int(data[j])
        data = str(input()).split(" ")
        for j in range(m):
            b[j] = int(data[j])

        # 时间复杂度：O(nlogn)
        a.sort()

        ans = float("inf")
        left, right = 0, n - 1
        # 对 a 应用二分
        # 时间复杂度：O(mlogn)
        while left <= right:
            mid = left + (right - left) // 2
            min_square = float("inf")
            # 对 b 的话线性扫描
            for j in range(m):
                square = (a[mid] - b[j]) ** 2
                if abs(square - k * k) < ans:
                    min_square = square
                    ans = abs(square - k * k)
            if min_square == k * k:
                break
            # 使用满足 abs(square - k * k) 最小的 square 作为二分的条件
            # 如果 min_square 比 k^2 要小的话，那应该去搜索 a 的右半区
            # 因为左半区的元素更小，那么 min_square 就会更小，那么 abs(min_square - k^2) 只会更大
            # 去右半区的话，那么 min_square 就有可能会变大，那么 abs(min_square - k^2) 可能会比之前更小
            if min_square < k * k:
                left = mid + 1
            else:
                right = mid - 1
        print(ans)
