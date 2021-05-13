class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        words = s.split()
        if len(pattern) != len(words): return False

        char2Word, word2Char = {}, {}
        for c, word in zip(pattern, words):
            if ((c in char2Word and char2Word[c] != word)
                    or (word in word2Char and word2Char[word] != c)):
                return False
            char2Word[c], word2Char[word] = word, c
        return True