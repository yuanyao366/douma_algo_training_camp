class TwoSum:

    def __init__(self):
        self.nums = []
        self.is_sorted = False

    def add(self, number: int) -> None:
        self.nums.append(number)
        self.is_sorted = False


    def find(self, value: int) -> bool:
        if not self.is_sorted:
            self.nums.sort()
            self.is_sorted = True

        left, right = 0, len(self.nums) - 1
        while left < right:
            lr_sum = self.nums[left] + self.nums[right]
            if lr_sum == value:
                return True
            elif lr_sum < value:
                left += 1
            else:
                right -= 1
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)