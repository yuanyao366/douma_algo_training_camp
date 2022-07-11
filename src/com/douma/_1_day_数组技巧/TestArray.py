# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


def countArray1(arr):
    countMap = {}
    for num in arr:
        if num in countMap:
            countMap[num] = countMap[num] + 1
        else:
            countMap[num] = 1
    return countMap


def countArray2(arr):
    countMap = [0] * 6
    for num in arr:
        # 元素值作为索引下标
        index = num - 1
        countMap[index] += 1
    return countMap


print(countArray2([1, 2, 3, 1, 1]))