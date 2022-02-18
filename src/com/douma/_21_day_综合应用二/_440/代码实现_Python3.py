class Solution:
    def findKthNumber(self, n: int, k: int) -> int:

        def calNodes(n, curr, next) -> int:
            nodes = 0
            while curr <= n:
                nodes += min(n + 1, next) - curr
                curr, next = curr * 10, next * 10
            return nodes

        curr, k = 1, k - 1
        while k > 0:
            nodes = calNodes(n, curr, curr + 1)
            if nodes <= k:
                curr, k = curr + 1, k - nodes
            else:
                curr, k = curr * 10, k - 1
        return curr