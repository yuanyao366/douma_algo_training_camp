# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 得到一个数字 num 的所有的因子，并且拼接成字符串返回
# 时间复杂度：O(sqrt(m)), m 的大小是 2 * 10^4
def getFactorStr(num):
    res, i = "", 1
    while i * i <= num:
        if num % i == 0:
            res += str(i)
        i += 1
    # 注意，此时 i * i > num，所以要 i --
    i -= 1
    # 第二趟反向遍历，对 i 的初始值，还需要根据是否 i * i == num 做判断，避免重复
    if i * i == num:
        i -= 1
    while i >= 1:
        if num % i == 0:
            res += str(num // i)
        i -= 1
    return res


# issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4CCL0
# 时间复杂度：O(n * sqrt(m))，其中 n 的规模是 10^5， m 的规模大小为 2 * 10^4
# 总的数据规模最大是 10^7 ( = 10^5 * 10^2) ，
# 也就是相当于数据规模为 10^7，时间复杂度为 O(n)，注意这里的 n = 10^7，这个是不会超时的
if __name__ == '__main__':
    line1 = str(input()).split(" ")
    n, k = int(line1[0]), line1[1]

    line2 = str(input()).split(" ")
    nums = [0] * n
    for i in range(n):
        nums[i] = int(line2[i])

    res = 0
    for num in nums:
        if k in getFactorStr(num):
            res += 1
    print(res)
