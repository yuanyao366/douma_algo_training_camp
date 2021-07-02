from typing import List


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:

        phone = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz",
        }

        res = []
        if not digits: return res

        def find_combination(index, combination) -> None:
            if index == len(digits):
                res.append(combination[:])
                return
            for c in phone[digits[index]]:
                find_combination(index + 1, combination + c)

        find_combination(0, "")
        return res