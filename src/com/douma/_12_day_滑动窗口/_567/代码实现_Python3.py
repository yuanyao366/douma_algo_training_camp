class Solution:
    # 滑动窗口
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n, m = len(s1), len(s2)
        if n > m : return False

        cnt = [0] * 26
        for c in s1:
            cnt[ord(c) - ord('a')] += 1

        left = right = 0;
        while right < len(s2):
            x = ord(s2[right]) - ord('a')
            cnt[x] -= 1
            while cnt[x] < 0:
                cnt[ord(s2[left]) - ord('a')] += 1
                left += 1
            if right - left + 1 == n:
                return True
            right += 1
        return False