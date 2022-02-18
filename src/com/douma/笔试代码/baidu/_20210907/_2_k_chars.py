# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

data = str(input()).split(" ")
k = int(data[1])
s = str(input())

# 1. 计算所有小写字母出现的次数
# 并拿到出现的小写字母
cnt, chars = [0] * 26, set()
for c in s:
    cnt[ord(c) - ord('a')] += 1
    chars.add(c)

res, chars = 0, list(chars)


# 回溯从 chars 中挑选 k 个字母
# 并根据每个字母出现的次数，计算这 k 个字母对应组合数
# 时间复杂度：O((n！/(k！* (n-k)!) * k)，其中 n = 26
def dfs(index, path):
    if k == len(path):
        # 计算 k 个字母对应组合数
        """
        比如：字母 abc
        然后 a 出现的次数是 3，b 出现的次数是 2，c 出现的次数是 1
        那么排列的组合数是：
            (2^3 - 1) * (2^2 - 1) * (2^1 - 1) = 7 * 3 * 1 = 21
        """
        tmp = 1
        for c in path:
            tmp *= (pow(2, cnt[ord(c) - ord('a')]) - 1) % (pow(10, 9) + 7)
        global res
        res += tmp % (pow(10, 9) + 7)
    for i in range(index, len(chars)):
        path.append(chars[i])
        dfs(i + 1, path)
        path.pop()


# 2. 从出现的字母中挑选 k 个字母，并计算这 k 个字母的组合数
# 累加所有的组合数就是结果
dfs(0, [])

print(res)
