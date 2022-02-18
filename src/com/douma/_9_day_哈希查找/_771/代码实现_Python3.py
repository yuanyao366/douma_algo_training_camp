class Solution:
    def numJewelsInStones1(self, jewels: str, stones: str) -> int:
        jewelsSet = set(jewels)
        return sum(s in jewelsSet for s in stones)

    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        # count 中存储 A 到 Z 中的所有的字符，包含 58 个字符
        count = [0] * (ord('z') - ord('A') + 1)
        for c in jewels:
            count[ord(c) - ord('A')] = 1
        res = 0
        for c in stones:
            if count[ord(c) - ord('A')] == 1:
                res += 1
        return res