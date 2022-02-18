from typing import List


def checkStraightLine(self, coordinates: List[List[int]]) -> bool:
    x0, y0 = coordinates[0][0], coordinates[0][1]
    x = coordinates[1][0] - x0
    y = coordinates[1][1] - y0
    for i in range(2, len(coordinates)):
        xi = coordinates[i][0] - x0
        yi = coordinates[i][1] - y0

        if x * yi - y * xi != 0: return False

    return True
