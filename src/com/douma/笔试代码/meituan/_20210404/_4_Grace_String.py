# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 时间复杂度：O(n!)
# 空间复杂度：O(n!)
if __name__ == '__main__':
    s = str(input())

    # 1. 使用回溯，拿到字符串 str 的所有的子序列 (或者说子集)
    allSubsets, subset = [], []

    def dfs(startIndex):
        allSubsets.append(subset[:])
        for i in range(startIndex, len(s)):
            subset.append(s[i])
            dfs(i + 1)
            subset.pop()

    dfs(0)

    # 2. 将有重复字符的子序列过滤掉，并返回没有重复字符的子序列的数量
    def hasSameChar(subset):
        # 因为字符都是小写字母，所以可以用一个长度为 26 的数字来判重
        exists = [False] * 26
        for c in subset:
            if exists[ord(c) - ord('a')]:
                return True
            exists[ord(c) - ord('a')] = True
        return False

    def filter():
        res = 0
        for subset in allSubsets:
            if hasSameChar(subset):
                continue
            res += 1
        return res

    cnt = filter()
    print(cnt)
