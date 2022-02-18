# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 时间复杂度：O(n)
# 空间复杂度：O(1)
if __name__ == '__main__':
    s = str(input())
    char_cnt = [0] * 26
    for c in s:
        char_cnt[ord(c) - ord('a')] += 1

    res = 1
    for i in range(26):
        res *= (char_cnt[i] + 1)
        res %= 20210101

    print(res)
