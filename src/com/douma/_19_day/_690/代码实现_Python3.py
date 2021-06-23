from typing import List


"""
# Definition for Employee.
class Employee:
    def __init__(self, id: int, importance: int, subordinates: List[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates
"""

class Solution:
    def __init__(self):
        self.emp_map = {}

    def getImportance(self, employees: List['Employee'], id: int) -> int:
        self.emp_map = {emp.id: emp for emp in employees}

        return self.bfs(id)

    def dfs(self, id: int) -> int:
        emp, total = self.emp_map[id], 0
        for subordinate_id in emp.subordinates:
            total += self.dfs(subordinate_id)
        return emp.importance + total

    def bfs(self, id: int) -> int:
        queue, emp, total = deque(), self.emp_map[id], 0
        queue.append(emp)
        while len(queue):
            e = queue.popleft()
            total += e.importance
            for subordinate_id in e.subordinates:
                queue.append(self.emp_map[subordinate_id])
        return total