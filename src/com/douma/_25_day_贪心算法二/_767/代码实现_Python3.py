class Solution:
    def reorganizeString(self, s: str) -> str:
        n = len(s)
        # 1. 统计每个字符出现的次数
        count = [0] * 26
        for c in s:
            index = ord(c) - ord('a')
            count[index] += 1
            if count[index] > (n + 1) // 2:
                return ""

        # 2. 拿到出现次数最多的字符
        max_count_index = 0
        for i in range(26):
            if count[i] > count[max_count_index]:
                max_count_index = i

                # 3. 先将出现次数最多的字符放在偶数索引上
        res, idx = [''] * n, 0
        while count[max_count_index] > 0:
            res[idx] = chr(max_count_index + ord('a'))
            idx += 2
            count[max_count_index] -= 1

        # 4. 将其他的字符放到其他的位置
        for i in range(26):
            while count[i] > 0:
                if idx >= n: idx = 1
                res[idx] = chr(i + ord('a'))
                idx, count[i] = idx + 2, count[i] - 1

        return "".join(res)