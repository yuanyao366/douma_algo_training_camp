from typing import List


class Solution:
    # 贪心策略：每次先安排出现次数最多的任务
    def leastInterval(self, tasks: List[str], n: int) -> int:
        counter = [0] * 26
        max_count = count_max = 0
        for task in tasks:
            index = ord(task) - ord('A')
            counter[index] += 1
            if max_count == counter[index]:
                count_max += 1
            elif max_count < counter[index]:
                max_count = counter[index]
                count_max = 1

        part_count, part_len = max_count - 1, n - (count_max - 1)
        empty_slots = part_count * part_len
        available_tasks = len(tasks) - max_count * count_max
        idle = max(0, empty_slots - available_tasks)

        return len(tasks) + idle