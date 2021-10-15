# 抖码算法，让算法学习变得简单有趣

# k 次行动
# 时间复杂度：O(k)
# 空间复杂度：O(1)
if __name__ == '__main__':
    data = str(input()).split(" ")
    a, b, f, k = int(data[0]), int(data[1]), int(data[2]), int(data[3])

    # remain 表示剩余能量
    remain, res, not_arrive = b, 0, False
    for i in range(k):
        # 从 0 到 a
        if i % 2 == 0:
            # 剩余容量到不了充能量站 f， 或者充满了能量，也到不了 a
            if remain < f or b < a - f:
                not_arrive = True
                break
            else:
                # 满足两个条件就需要充能量：
                # 1. 当前不是最后一趟，并且剩余能量不足以下一趟到达 f 点
                # 2. 当前是最后一趟的话，那么剩余能量小于 a 的话，就需要充能量
                if (i != k - 1 and remain < a + (a - f)) or (i == k - 1 and remain < a):
                    res += 1
                    # 注意，充完能量后，能量最多就是 b，因为容量是 b
                    # 充完后，只需要减去从 f 到 a 消耗的能量即可
                    remain = b - (a - f)
                else:
                    remain = remain - a
        else:  # 从 a 到 0
            # 剩余容量到不了充能量站 f， 或者充满了能量，也到不了 0
            if remain < a - f or b < f:
                not_arrive = True
                break
            else:
                # 满足两个条件就需要充能量：
                # 1. 当前不是最后一趟，并且剩余能量不足以下一趟到达 f 点
                # 2. 当前是最后一趟的话，那么剩余能量小于 a 的话，就需要充能量
                if (i != k - 1 and remain < a + f) or (i == k - 1 and remain < a):
                    res += 1
                    # 注意，充完能量后，能量最多就是 b，因为容量是 b
                    # 充完后，只需要减去从 f 到 0 消耗的能量即可
                    remain = b - f
                else:
                    remain = remain - a

    print(-1) if not_arrive else print(res)