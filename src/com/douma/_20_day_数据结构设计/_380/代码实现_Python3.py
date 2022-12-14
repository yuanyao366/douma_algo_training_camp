class RandomizedSet:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.idx_map = {}
        self.nums = []


    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        if val in self.idx_map: return False
        self.idx_map[val] = len(self.nums)
        self.nums.append(val)
        return True


    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val not in self.idx_map: return False
        index, last_num = self.idx_map[val], self.nums[-1]
        self.nums[index] = last_num
        self.idx_map[last_num] = index
        self.nums.pop()
        del self.idx_map[val]
        return True


    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return choice(self.nums)



# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()