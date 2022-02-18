class Solution:
    def minWindow(self, s: str, t: str) -> str:
        cntT, unique_chars = [0] * 60, 0
        for c in t:
            if cntT[ord(c) - ord('A')] == 0:
                unique_chars += 1
            cntT[ord(c) - ord('A')] += 1

        filtered_s = []
        for i in range(len(s)):
            if cntT[ord(s[i]) - ord('A')] > 0:
                filtered_s.append((i, s[i]))

        left = right = 0
        window_cnt, matched_chars = [0] * 60, 0
        ans = [-1, 0, 0]
        while right < len(filtered_s):
            right_char = filtered_s[right][1]
            right_char_index = ord(right_char) - ord('A')
            window_cnt[right_char_index] += 1

            if window_cnt[right_char_index] == cntT[right_char_index]:
                matched_chars += 1

            while left <= right and matched_chars == unique_chars:
                start, end = filtered_s[left][0], filtered_s[right][0]
                if ans[0] == -1 or end - start + 1 < ans[0]:
                    ans = [end - start + 1, start, end]
                left_char_index = ord(filtered_s[left][1])- ord('A')
                window_cnt[left_char_index] -= 1
                if window_cnt[left_char_index] < cntT[left_char_index]:
                    matched_chars -= 1
                left += 1

            right += 1

        return "" if ans[0] == -1 else s[ans[1]:ans[2] + 1]