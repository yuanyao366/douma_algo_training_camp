# 抖码算法，让算法学习变得简单有趣
# 作者：老汤
# 官方微信：douma22334
# 公众号：抖码课堂

# 时间复杂度：O(n^2)
# 空间复杂度：O(n)
if __name__ == '__main__':
    # 1. 输入数据的处理，转成 int 类型的数组
    data = str(input()).split(",")
    n = len(data)
    nums = [0] * n
    for i in range(n):
        nums[i] = int(data[i])

    # 2. 求符合条件的所有子序列
    res, visited = [], [False] * n
    for i in range(n):
        if visited[i]:
            continue
        sub_seq, visited[i], prevNum = [nums[i]], True, nums[i]
        for j in range(i + 1, n):
            if visited[j]:
                continue
            if nums[j] < prevNum:
                sub_seq.append(nums[j])
                visited[j], prevNum = True, nums[j]
        res.append(sub_seq)

    print(res)
