class Solution:
    def maximumSwap(self, num: int) -> int:
        # 计算每位上数字出现的最后索引位置
        chars, last = list(str(num)), [0] * 26
        for i in range(len(chars)):
            last[ord(chars[i]) - ord('0')] = i

        # 从高位到低位遍历
        # 对于每一位，如果后面有比这一位大的数字，则交换，然后返回
        for i in range(len(chars)):
            for d in range(9, ord(chars[i]) - ord('0'), -1):
                if last[d] > i:
                    chars[i], chars[last[d]] = chars[last[d]], chars[i]
                    return int("".join(chars))

        return num