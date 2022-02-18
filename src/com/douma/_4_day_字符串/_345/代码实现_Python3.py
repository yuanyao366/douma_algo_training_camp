def is_vowel(self, c: str) -> bool:
    return c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u' or c == 'A' or c == 'E' or c == 'I' or c == 'O' or c == 'U'


def reverseVowels(self, s: str) -> str:
    s = list(s)
    left, right = 0, len(s) - 1
    while left < right:
        while left < right and not self.is_vowel(s[left]): left += 1
        while left < right and not self.is_vowel(s[right]): right -= 1

        s[left], s[right] = s[right], s[left]
        left, right = left + 1, right - 1
    return ''.join(s)
