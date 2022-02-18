s, res = str(input()), ""
for i in range(len(s) - 1, -1, -1):
    res += s[i]
print(res)