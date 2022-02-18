class Solution:
    def __init__(self):
        self.ans = 0

    def pathSum(self, nums: List[int]) -> int:
        tree = [-1]*16
        for num in nums:
            bai, shi, ge = num // 100, num % 100 // 10, num % 10
            index = (1 << (bai - 1)) - 1 + shi - 1
            tree[index] = ge

        def dfs(i: int, sum: int) -> None:
            if tree[i] == -1: return
            sum += tree[i]
            if (i + 1 >= 8) or (tree[2 * i + 1] == -1 and tree[2 * i + 2] == -1):
                self.ans += sum
                return
            dfs(2 * i + 1, sum)
            dfs(2 * i + 2, sum)

        dfs(0, 0)
        return self.ans