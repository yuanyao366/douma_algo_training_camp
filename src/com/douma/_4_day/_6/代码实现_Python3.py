def convert(self, s: str, numRows: int) -> str:
    if numRows == 1: return s

    ret = [""] * min(len(s), numRows)
    currRow, goingDown = 0, False
    for c in s:
        ret[currRow] += c
        if currRow == 0 or currRow == numRows - 1:
            goingDown = not goingDown
        if goingDown: currRow += 1
        else: currRow -= 1

    for i in range(1, len(ret)):
        ret[0] += ret[i]

    return ret[0]