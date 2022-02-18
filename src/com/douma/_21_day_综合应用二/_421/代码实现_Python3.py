class Node:
    def __init__(self):
        self.zero = None
        self.one = None

class Trie:
    def __init__(self):
        self.root = Node()

    def add(self, num) -> None:
        curr = self.root
        for k in range(30, -1, -1):
            bit = (num >> k) & 1
            if bit == 0:
                if not curr.zero:
                    curr.zero = Node()
                curr = curr.zero
            else:
                if not curr.one:
                    curr.one = Node()
                curr = curr.one

    def max_xor(self, num) -> int:
        curr, x = self.root, 0
        for k in range(30, -1, -1):
            bit = (num >> k) & 1
            if bit == 0:
                if curr.one:
                    curr, x = curr.one, 2 * x + 1
                else:
                    curr, x = curr.zero, 2 * x
            else:
                if curr.zero:
                    curr, x = curr.zero, 2 * x + 1
                else:
                    curr, x = curr.one, 2 * x
        return x


class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        trie, res = Trie(), 0
        for i in range(1, len(nums)):
            trie.add(nums[i - 1])
            res = max(res, trie.max_xor(nums[i]))
        return res