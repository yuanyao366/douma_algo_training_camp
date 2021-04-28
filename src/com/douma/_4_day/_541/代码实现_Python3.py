def reverseStr(self, s: str, k: int) -> str:
    a = list(s)
    for start in range(0, len(a), 2 * k):
        left, right = start, min(start + k - 1, len(a) - 1)
        while left < right:
            a[left], a[right] = a[right], a[left]
            left, right = left + 1, right - 1
    return "".join(a)