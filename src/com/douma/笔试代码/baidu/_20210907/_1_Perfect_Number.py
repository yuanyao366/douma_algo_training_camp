# 抖码算法，让算法学习变得简单有趣
# 作者：菲菲


def f(n):
    chars = list(str(n))
    digits_len, has_zero = len(chars), False
    for i in range(digits_len):
        c = chars[i]
        if c > '3':
            # 如果数字大于 3，那么就将这一位设置为 3
            chars[i] = '3'
        elif c == '0':
            # 1. 将前一位减 1
            chars[i] = '3'
            j = i - 1
            while j >= 0:
                chars[j] = chr(ord(chars[j]) - 1)
                # 如果前一位减 1 后，当前位仍然大于等于 1，那么直接退出
                if ord(chars[j]) >= ord('1') or j == 0:
                    break
                else:
                    # 如果前一位减 1 后，当前位等于 0 的话，那么设置为 3
                    # 然后继续将前一位减 1
                    chars[j] = '3'
                j -= 1

            # 2. 将当前位以及后面所有位设置为 3
            while i < digits_len:
                chars[i] = '3'
                i += 1

            # 3. 如果第一位减去 1 后，等于 0 ，那么结果将从第二位开始
            if chars[0] == '0':
                return int("".join(chars[1:]))

            # 4. 不用再处理了
            break

    return int("".join(chars))


t = int(input())
nums = [0] * t
for i in range(t):
    nums[i] = int(input())
for num in nums:
    print(f(num))


