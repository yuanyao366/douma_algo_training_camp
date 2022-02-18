class Solution:
    # 哈希集合
    def lengthOfLongestSubstring1(self, s: str) -> int:
        n = len(s)
        if n <= 1: return n
        max_len, window = 0, set()
        left = right = 0
        while right < n:
            if s[right] not in window:
                max_len = max(max_len, right - left + 1)
                window.add(s[right])
                right += 1
            else:
                window.remove(s[left])
                left += 1
        return max_len

    # 哈希映射
    def lengthOfLongestSubstring2(self, s: str) -> int:
        n = len(s)
        if n <= 1: return n
        max_len, window = 0, {}
        left = right = 0
        while right < n:
            right_char_index = window.get(s[right], -1)
            # 下面这样代码的详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P
            left = max(left, right_char_index)
            max_len = max(max_len, right - left + 1)
            window[s[right]] = right + 1
            right += 1
        return max_len

    # 字符数组
    def lengthOfLongestSubstring(self, s: str) -> int:
        n = len(s)
        if n <= 1: return n
        max_len, window = 0, [0]*128
        left = right = 0
        while right < n:
            right_char_index = window[ord(s[right])]
            left = max(left, right_char_index)
            max_len = max(max_len, right - left + 1)
            window[ord(s[right])] = right + 1
            right += 1
        return max_len