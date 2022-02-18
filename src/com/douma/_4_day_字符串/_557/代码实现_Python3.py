def reverseWords(self, s: str) -> str:
    n, arr, left = len(s), list(s), 0

    while left < n:
        if arr[left] != ' ':
            right = left
            while right + 1 < n and arr[right + 1] != ' ':
                right += 1
            self.reverseWord(arr, left, right)
            left = right + 1
        else:
            left += 1

    return "".join(arr)


def reverseWord(self, arr, left, right) -> None:
    while left < right:
        arr[left], arr[right] = arr[right], arr[left]
        left, right = left + 1, right - 1


# 函数式编程
def reverseWords1(self, s: str) -> str:
    return " ".join(word[::-1] for word in s.split(" "))
