# 抖码算法，让算法学习变得简单有趣
# 作者：小莹

data = str(input()).split(" ")
n = int(data[0])
k = int(data[1])
s = str(input())

# 1. 统计每个字母出现的次数，并且记录总共出现的字母种类
cnt, kind = [0] * 26, 0
for i in range(n):
    index = ord(s[i]) - ord('a')
    cnt[index] += 1
    if cnt[index] == 1:
        kind += 1

# 2. 如果总的字母种类都没有 k 大，那么直接退出
if kind < k:
    print(0)
    exit(0)

res = left = right = 0
while right < n:
    # 删除 right 对应的字符
    index = ord(s[right]) - ord('a')
    cnt[index] -= 1
    # 如果 right 字符出现的次数变为 0，则种类数减一
    if cnt[index] == 0:
        kind -= 1
    # 如果种类数小于 k ，那么说明不能删除 [left...right] 中所有的字符
    # 只能删除 [left...right - 1] 这个窗口的中字符
    if kind < k:
        """
        在窗口 [left...right - 1] 中计算所有的连续子串的个数
        比如:
        这个窗口的字符串是 aab ，那么就有 6 个连续的子串 (3 * 4 / 2)
        这个窗口的字符串是 aaba ，那么就有 10 个连续的子串 (4 * 5 / 2)
        """
        m = right - left
        res += m * (m + 1) // 2
        # 移动 left，缩小窗口，恢复 [left...right - 1] 中的字符
        while left < right:
            index = ord(s[left]) - ord('a')
            cnt[index] += 1
            if cnt[index] == 1:
                kind += 1
            left += 1
    # 如果可以删除的话，尽量的扩大窗口，找到最大的可以删除的连续子数组
    right += 1

# 如果最后一个窗口不是空的话，那么这些字符串都是可以删除的
if kind >= k:
    m = right - left
    res += m * (m + 1) // 2

print(res)
