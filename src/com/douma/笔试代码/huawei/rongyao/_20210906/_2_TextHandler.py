# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

# 处理输入数据
s, L = str(input()), int(input())
words = s.split(",")


def process_last_window(left, right):
    line, chars_len = "", 0
    # 1. 单词与单词之间仅需一个 *
    for i in range(left, right + 1):
        line += words[i]
        chars_len += len(words[i])
        if chars_len < L:
            line, chars_len = line + "*", chars_len + 1
    # 2. 剩余位置用 * 补足
    while chars_len < L:
        line, chars_len = line + "*", chars_len + 1
    return line


def get_word_gaps(left, right):
    # 1. 先计算除了单词的长度，剩余的长度大小
    remain = L
    for i in range(left, right + 1):
        remain -= len(words[i])
    # 2. 计算间隔的数量
    gap_cnt = right - left
    # 3. 计算每个间隔的长度
    # 单词间的 * 应均匀分布，无法均匀分布的话，则左边可以比右边多一个 *
    # 比如间隔是 3， 剩余长度是 8 的话，那么间隔大小为：[3, 3, 2]
    gaps = [0] * gap_cnt
    a, b = remain // gap_cnt, remain % gap_cnt
    for i in range(gap_cnt):
        gaps[i] = a
        if i < b:
            gaps[i] += 1
    return gaps


def process_non_last_window(left, right):
    line, chars_len = "", len(words[left])
    # 1. 第一个单词与左边对齐
    line += words[left]

    # 2. 如果只有一个单词的话，那么剩下的位置用 * 补足
    if right - left + 1 == 1:
        while chars_len < L:
            line, chars_len = line + "*", chars_len + 1
    else:
        # 3. 有两个或者两个以上的单词
        # 3.1 先计算中间每个单词之间的长度
        gaps = get_word_gaps(left, right)
        # 3.2 根据每个间隔之间的长度填充字符串
        for i in range(len(gaps)):
            # 先填充 *
            for _ in range(gaps[i]):
                line += "*"
            # 再填充单词
            left += 1
            line += words[left]
    return line


# 时间复杂度：O(n)，n 表示字符串的长度
# 空间复杂度：O(n)
# 使用滑动窗口处理文本字符串
res, left, right, window_len = [], 0, 0, 0
while right < len(words):
    # 这里加 1 的原因是：每个单词之间至少会有 1 个 *
    if right > left:
        window_len += 1
    window_len += len(words[right])

    # 处理最后一个窗口
    if right == len(words) - 1:
        res.append(process_last_window(left, right))
        right += 1
    elif window_len > L:
        # 处理非最后一个窗口
        # 因为要处理的窗口的长度 <= L，所以这里是 right - 1
        res.append(process_non_last_window(left, right - 1))
        # 处理完当前窗口，将处理下一个窗口，并清零 windowLen
        left, window_len = right, 0
    else:
        right += 1

for line in res:
    print(line)
