class Solution:
    # 排序查找
    def containsDuplicate1(self, nums: List[int]) -> bool:
        nums.sort()
        for i in range(1, len(nums)):
            if nums[i - 1] == nums[i]:
                return True
        return False

    # 哈希查找
    def containsDuplicate2(self, nums: List[int]) -> bool:
        visited = {}
        for i in range(len(nums)):
            if visited.get(nums[i]):
                return True
            visited[nums[i]] = 1
        return False

    # 哈希，代码简化
    def containsDuplicate(self, nums: List[int]) -> bool:
        return len(nums) != len(set(nums))