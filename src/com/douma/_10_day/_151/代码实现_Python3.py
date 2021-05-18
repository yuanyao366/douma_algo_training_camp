class Solution:
    # 使用内置 API
    def reverseWords1(self, s: str) -> str:
        return " ".join(reversed(s.split()))

    # 不使用内置 API
    def reverseWords2(self, s: str) -> str:
        # 1. 消除字符串 s 中的所有空格
        s_without_spaces = self.trim_spaces(s);

        # 2. 反转整个字符串
        self.reverse(s_without_spaces, 0, len(s_without_spaces) - 1)

        # 3. 反转每个单词
        self.reverse_each_word(s_without_spaces)
        return ''.join(s_without_spaces)

    def trim_spaces(self, s: str) -> list:
        left, right = 0, len(s) - 1
        # 去掉左右两端的空格
        while left <= right and s[left] ==' ':
            left += 1
        while left <= right and s[right] == ' ':
            right -= 1

        # 去掉中间多余的空格
        output = []
        while left <= right:
            if s[left] != ' ':
                output.append(s[left])
            elif output[-1] != ' ':
                # 保证单词与单词之间有一个空格
                output.append(' ')
            left += 1

        return output

    def reverse(self, s: list, left: int, right: int) -> None:
        while left < right:
            s[left], s[right] = s[right], s[left]
            left, right = left + 1, right - 1


    def reverse_each_word(self, s: list) -> None:
        left = 0
        while left < len(s):
            if s[left] != ' ':
                right = left
                while right + 1 < len(s) and s[right + 1] != ' ':
                    right += 1
                self.reverse(s, left, right)
                left = right + 1
            else:
                left += 1

    # 双端队列
    def reverseWords(self, s: str) -> str:
        left, right = 0, len(s) - 1
        # 去掉左右两端的空格
        while left < right and s[left] ==' ':
            left += 1
        while left < right and s[right] == ' ':
            right -= 1

        deque = collections.deque()
        word = []
        while left <= right:
            if s[left] != ' ':
                word.append(s[left])
            elif word:
                deque.appendleft(''.join(word))
                word = []
            left += 1
        deque.appendleft(''.join(word))
        return ' '.join(deque)