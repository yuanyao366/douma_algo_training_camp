class Solution:
    # 贪心策略：只有在开头和结尾两个字符不相等的时候，才去尝试删除开头或者结尾任一个字符
    # 时间复杂度：O(n)
    # 空间复杂度：O(1)
    def validPalindrome(self, s: str) -> bool:
        if len(s) <= 1: return True

        def validPalindrome(s, left, right) -> bool:
            while left < right:
                if s[left] != s[right]:
                    return False
                left, right = left + 1, right - 1
            return True

        left, right = 0, len(s) - 1
        while left < right:
            if s[left] == s[right]:
                left, right = left + 1, right - 1
            else:
                return validPalindrome(s, left + 1, right) or validPalindrome(s, left, right - 1)

        return True