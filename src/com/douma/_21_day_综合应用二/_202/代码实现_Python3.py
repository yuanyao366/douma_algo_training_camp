class Solution:
    def square_sum(self, n: int) -> int:
        sum = 0
        while n:
            sum += (n % 10)**2
            n //= 10
        return sum

    # 哈希表
    def isHappy1(self, n: int) -> bool:
        visited = set()
        while True:
            if n == 1:
                return True
            if n in visited:
                return False
            visited.add(n)
            n = self.square_sum(n)

    # 快慢指针
    def isHappy(self, n: int) -> bool:
        if n == 1: return True
        slow = fast = n
        while True:
            slow = self.square_sum(slow)
            fast = self.square_sum(self.square_sum(fast))
            if slow == 1 or fast == 1:
                return True
            if slow == fast:
                return False