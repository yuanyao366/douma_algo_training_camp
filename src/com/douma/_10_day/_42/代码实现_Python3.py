from typing import List


class Solution:
    # 预计算最大值
    def trap1(self, height: List[int]) -> int:
        n = len(height)
        if n <= 2: return 0

        left_max = [0] * n
        left_max[0] = height[0]
        for i in range(1, n):
            left_max[i] = max(left_max[i - 1], height[i])

        right_max = [0] * n
        right_max[n - 1] = height[n - 1]
        for i in range(n - 2, -1, -1):
            right_max[i] = max(right_max[i + 1], height[i])

        ans = 0
        for i in range(1, n - 1):
            max_height = min(left_max[i], right_max[i])
            if max_height > height[i]:
                ans += max_height - height[i]

        return ans

    # 双指针
    def trap2(self, height: List[int]) -> int:
        n = len(height)
        if n <= 2: return 0

        left_max = right_max = 0
        left, right, ans = 0, n - 1, 0

        while left < right:
            left_max = max(left_max, height[left])
            right_max = max(right_max, height[right])
            if height[left] < height[right]:
                ans += left_max - height[left]
                left += 1
            else:
                ans += right_max - height[right]
                right -= 1

        return ans

    # 单调栈
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n <= 2: return 0

        ans, stack = 0, []
        for i in range(n):
            while stack and height[i] > height[stack[-1]]:
                top = stack[-1]
                stack.pop()
                if not stack: break

                left_index = stack[-1]
                curr_width = i - left_index - 1
                curr_height = min(height[left_index], height[i]) - height[top]
                ans += curr_height * curr_width
            stack.append(i)

        return ans