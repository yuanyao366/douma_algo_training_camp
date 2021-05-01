
def convert(self, s: str, num_rows: int) -> str:
    if num_rows == 1: return s

    ret = [""] * min(len(s), num_rows)
    curr_row, going_down = 0, False
    for c in s:
        ret[curr_row] += c
        if curr_row == 0 or curr_row == num_rows - 1:
            going_down = not going_down
        if going_down: curr_row += 1
        else: curr_row -= 1

    for i in range(1, len(ret)):
        ret[0] += ret[i]

    return ret[0]