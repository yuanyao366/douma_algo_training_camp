from typing import List


class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        one_word_len, word_num = len(words[0]), len(words)
        if len(s) < one_word_len: return []

        words_cnt = {word: 0 for word in words}
        for word in words:
            words_cnt[word] += 1
        ans = []
        for i in range(one_word_len):
            left = right = i
            matched_words, window_map = 0, {}
            while right <= len(s) - one_word_len:
                curr_word = s[right : right + one_word_len]
                if curr_word in window_map:
                    window_map[curr_word] += 1
                else:
                    window_map[curr_word] = 1
                matched_words += 1
                while window_map.get(curr_word, 0) > words_cnt.get(curr_word, 0):
                    left_word = s[left : left + one_word_len]
                    window_map[left_word] -= 1
                    left += one_word_len
                    matched_words -= 1
                if matched_words == word_num:
                    ans.append(left)
                right += one_word_len
        return ans