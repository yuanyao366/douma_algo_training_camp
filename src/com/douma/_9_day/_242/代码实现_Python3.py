class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        count = {}
        for c in s:
            if c in count: count[c] += 1
            else: count[c] = 1
        for c in t:
            if not c in count or count[c] <= 0: return False
            count[c] -= 1
        return True

    def isAnagram1(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        count = [0] * 26
        for c in s: count[ord(c) - ord('a')] += 1
        for c in t:
            if count[ord(c) - ord('a')] <= 0: return False
            count[ord(c) - ord('a')] -= 1
        return True