def countAndSay(self, n: int) -> str:
    curr = "1"
    for i in range(1, n):
        prev, curr = curr, ""
        say, count = prev[0], 1
        for j in range(1, len(prev)):
            if prev[j] == say:
                count += 1
            else:
                curr += str(count)
                curr += say
                say, count = prev[j], 1
        curr += str(count)
        curr += say
    return curr