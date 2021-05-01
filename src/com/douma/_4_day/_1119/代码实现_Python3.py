def is_vowel(self, c: str) -> bool:
    return c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u'


def removeVowels(self, s: str) -> str:
    res = ''
    for c in s:
        if not self.is_vowel(c): res += c
    return res
