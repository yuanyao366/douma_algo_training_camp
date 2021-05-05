def totalHammingDistance(self, nums: List[int]) -> int:
    n, cnt = len(nums), [0] * 32
    for num in nums:
        i = 0
        while num > 0:
            cnt[i] += (num & 1)
            num, i = num >> 1, i + 1
    res = 0
    for k in cnt:
        res += k * (n - k)
    return res