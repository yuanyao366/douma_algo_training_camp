# 抖码算法，让算法学习变得简单有趣
# 作者：老汤
# 官方微信：bigdatatang01
#   官方公众号：抖码课堂

# 使用空格替换连续 2 个以及更多的 -
def trim_bar(s, n):
    i, output = 0, []
    while i < n:
        if s[i] == '-':
            cnt = 0
            while i < n and s[i] == '-':
                cnt, i = cnt + 1, i + 1
            if cnt >= 2:
                output.append(' ')
            else:
                output.append(s[i - 1])
        else:
            output.append(s[i])
            i += 1
    return output


def is_valid_char(c):
    return ('a' <= c <= 'z') or ('A' <= c <= 'Z') or ('0' <= c <= '9') or c == '-'


def trim_spaces(s):
    left, right = 0, len(s) - 1
    # 去掉左右两端的空格
    while left <= right and not is_valid_char(s[left]):
        left += 1
    while left <= right and not is_valid_char(s[right]):
        right -= 1

    # 去掉中间多余的空格
    output = []
    while left <= right:
        if is_valid_char(s[left]):
            output.append(s[left])
        elif output[-1] != ' ':
            # 保证单词与单词之间有一个空格
            output.append(' ')
        left += 1

    return output


def reverse(s, left, right):
    while left < right:
        s[left], s[right] = s[right], s[left]
        left, right = left + 1, right - 1


def reverse_each_word(s):
    left = 0
    while left < len(s):
        if s[left] != ' ':
            right = left
            while right + 1 < len(s) and s[right + 1] != ' ':
                right += 1
            reverse(s, left, right)
            left = right + 1
        else:
            left += 1


# 时间复杂度为 O(n)
# 空间复杂度为 O(n)
if __name__ == '__main__':
    s = str(input())
    """
    输入：s = "  Bob&123---Loves -Alice--abc   "
    第一步. 将空格替换连续 2 个以及更多的 -，得到：s = "  Bob&123 Loves -Alice abc   "
    第二步. 将非法字符当成空格，并且去掉多余的空格，得到：s = "Bob 123 Loves -Alice abc"
    第三步. 反转字符串，得到："cba ecilA- sevoL 321 boB"
    第四步. 反转每个单词，得到结果："abc -Alice Loves 123 Bob"
    """
    # 1. 将空格替换连续 2 个以及更多的 -
    s = trim_bar(s, len(s))

    # 2. 将非法字符当成空格，并且去掉多余的空格
    s_without_spaces = trim_spaces(s)

    # 3. 反转整个字符串
    reverse(s_without_spaces, 0, len(s_without_spaces) - 1)

    # 4. 反转每个单词
    reverse_each_word(s_without_spaces)

    print(''.join(s_without_spaces))
