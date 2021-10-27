# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


# 排序字符串
if __name__ == '__main__':
    def is_char(c):
        return 'A' <= c <= 'Z' or 'a' <= c <= 'z'

    def is_digit(c):
        return '0' <= c <= '9'

    s = str(input())

    # 1. 将字母和数字分开
    chars, nums = [], []
    for i in range(len(s)):
        if is_char(s[i]):
            chars.append(s[i])
        elif is_digit(s[i]):
            nums.append(s[i])

    # 2. 对字母升序排列
    chars.sort()
    # 对数字降序排列
    nums.sort(reverse=True)

    # 3. 根据排序后的字母以及数字，再根据原先字母和数字的位置
    # 拼接有序的字母和数字
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
