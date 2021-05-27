class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        ans = left = right = 0
        window_char_cnt = {}
        while right < len(s):
            curr_char = s[right]
            if curr_char in window_char_cnt:
                window_char_cnt[curr_char] += 1
            else:
                window_char_cnt[curr_char] = 1

            while left <= right and len(window_char_cnt) > 2:
                left_char = s[left]
                window_char_cnt[left_char] -= 1
                if window_char_cnt[left_char] <= 0:
                    del window_char_cnt[left_char]
                left += 1

            ans = max(ans, right - left + 1)
            right += 1

        return ans