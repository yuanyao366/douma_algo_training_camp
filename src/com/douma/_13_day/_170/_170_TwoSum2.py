class TwoSum:

    def __init__(self):
        self.nums = dict()

    def add(self, number: int) -> None:
        if number in self.nums:
            self.nums[number] += 1
        else:
            self.nums[number] = 1


    def find(self, value: int) -> bool:
        for key in self.nums.keys():
            target = value - key
            if target == key and self.nums[target] > 1:
                return True
            if target != key and target in self.nums and self.nums[target] > 0:
                return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)