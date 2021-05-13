from typing import List


class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        edge_freq, max_freq = {}, 0
        for row in range(len(wall)):
            edge_position = 0
            for col in range(len(wall[row]) - 1):
                curr_brick_length = wall[row][col]
                edge_position += curr_brick_length
                if edge_position in edge_freq:
                    edge_freq[edge_position] += 1
                else:
                    edge_freq[edge_position] = 1
                max_freq = max(max_freq, edge_freq[edge_position])
        return len(wall) - max_freq