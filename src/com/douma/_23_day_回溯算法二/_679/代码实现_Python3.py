from typing import List


class Solution:
    def judgePoint24(self, cards: List[int]) -> bool:
        TARGET, EPSILON = 24, 1e-6
        ADD, MULTIPLY, SUBTRACT, DIVIDE = 0, 1, 2, 3

        def dfs(list: List[float]) -> bool:
            if len(list) == 0: return False
            if len(list) == 1: return abs(list[0] - TARGET) < EPSILON

            for i in range(len(list)):
                for j in range(len(list)):
                    if i != j:
                        child_list = [list[k] for k in range(len(list)) if k != i and k != j]
                        for k in range(4):
                            if k < 2 and i > j: continue
                            if k == ADD: child_list.append(list[i] + list[j])
                            elif k == MULTIPLY: child_list.append(list[i] * list[j])
                            elif k == SUBTRACT: child_list.append(list[i] - list[j])
                            elif k == DIVIDE:
                                if abs(list[j]) < EPSILON: continue
                                else: child_list.append(list[i] / list[j])
                            if dfs(child_list): return True
                            child_list.pop()
            return False

        return dfs(cards)