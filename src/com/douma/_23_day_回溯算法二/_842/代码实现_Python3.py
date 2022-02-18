from typing import List


class Solution:
    def splitIntoFibonacci(self, num: str) -> List[int]:

        res = []
        def dfs(start_index, prev_two_num_sum, prev_num) -> bool:
            if start_index == len(num):
                return len(res) >= 3
            curr_num = 0
            for i in range(start_index, len(num)):
                if i > start_index and num[start_index] == '0':
                    break

                curr_num = curr_num * 10 + (ord(num[i]) - ord('0'))
                if curr_num > 2**31 - 1:
                    break

                if len(res) >= 2:
                    if curr_num < prev_two_num_sum: continue
                    elif curr_num > prev_two_num_sum: break

                res.append(curr_num)
                if dfs(i + 1, prev_num + curr_num, curr_num):
                    return True
                res.pop()
            return False

        dfs(0, 0, 0)
        return res