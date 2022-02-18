def multiply(self, num1: str, num2: str) -> str:
    if num1 == "0" or num2 == "0": return "0"
    m, n = len(num1), len(num2)
    res = [0] * (m + n)
    for i in range(m - 1, -1, -1):
        x = int(num1[i])
        for j in range(n - 1, -1, -1):
            y = int(num2[j])
            sum = res[i + j + 1] + x * y
            res[i + j + 1] = sum % 10
            res[i + j] += sum // 10
    index = 1 if res[0] == 0 else 0
    return "".join(str(x) for x in res[index:])