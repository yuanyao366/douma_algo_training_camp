def findRightSmall(self, nums: List[int]) -> List[int]:
    ans = [-1] * len(nums)
    stack = []
    for i in range(len(nums)):
        x = nums[i]
        # 单调递减栈
        while not stack and x > nums[stack[-1]]:
            ans[stack[-1]] = i
            stack.pop()
        stack.append(i)
    return ans