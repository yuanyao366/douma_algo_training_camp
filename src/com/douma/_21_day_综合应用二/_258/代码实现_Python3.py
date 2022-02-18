class Solution:
    def addDigits(self, num: int) -> int:

        def total_sum(num) -> int:
            total = 0
            while num:
                total += num % 10
                num //= 10
            return total

        while num >= 10:
            num = total_sum(num)

        return num