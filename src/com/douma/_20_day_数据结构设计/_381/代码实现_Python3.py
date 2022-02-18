class RandomizedCollection:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.idx_map = defaultdict(set)
        self.nums = []


    def insert(self, val: int) -> bool:
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        """
        self.idx_map[val].add(len(self.nums))

        self.nums.append(val)
        return len(self.idx_map[val]) == 1


    def remove(self, val: int) -> bool:
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        """
        if val not in self.idx_map: return False
        index, last_num = self.idx_map[val].pop(), self.nums[-1]
        self.nums[index] = last_num

        # 因为 index 在上面已经被 pop 了
        # self.idx_map[val].remove(index)
        if index < len(self.nums) - 1:
            self.idx_map[last_num].remove(len(self.nums) - 1)
            self.idx_map[last_num].add(index)

        self.nums.pop()
        if len(self.idx_map[val]) == 0:
            del self.idx_map[val]
        return True


    def getRandom(self) -> int:
        """
        Get a random element from the set.
        """
        return choice(self.nums)



# Your RandomizedCollection object will be instantiated and called as such:
# obj = RandomizedCollection()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()