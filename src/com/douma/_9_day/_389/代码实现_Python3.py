class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        ret = 0
        for c in s: ret ^= ord(c)
        for c in t: ret ^= ord(c)
        return chr(ret)

    def findTheDifference2(self, s: str, t: str) -> str:
        sumS = sumT = 0
        for c in s: sumS += ord(c)
        for c in t: sumT += ord(c)
        return chr(sumT - sumS)

    def findTheDifference1(self, s: str, t: str) -> str:
        count = [0] * 26
        for c in s: count[ord(c) - ord('a')] += 1
        for c in t:
            count[ord(c) - ord('a')] -= 1
            if count[ord(c) - ord('a')] < 0: return c
        return ' '