# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 排序字符串
if __name__ == '__main__':
    def is_char(c):
        return 'A' <= c <= 'Z' or 'a' <= c <= 'z'

    def is_digit(c):
        return '0' <= c <= '9'

    s = str(input())

    chars, nums = [], []
    for i in range(len(s)):
        if is_char(s[i]):
            chars.append(s[i])
        elif is_digit(s[i]):
            nums.append(s[i])

    chars.sort()
    nums.sort(reverse=True)

    chars_index, nums_index = 0, 0
    res = []
    for c in s:
        if is_char(c):
            res.append(chars[chars_index])
            chars_index += 1
        elif is_digit(c):
            res.append(nums[nums_index])
            nums_index += 1
        else:
            res.append('?')

    print("".join(res))
