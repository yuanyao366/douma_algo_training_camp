# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

if __name__ == '__main__':
    line = str(input()).split(" ")
    a, b = int(line[0]), int(line[1])

    line = str(input()).split(" ")
    candy_A = [0] * a
    for i in range(a):
        candy_A[i] = int(line[i])

    line = str(input()).split(" ")
    candy_B = [0] * b
    for i in range(b):
        candy_B[i] = int(line[i])

    # 1. 计算 A 的最大的前缀和
    max_A, prefix_A = 0, [0] * (a + 1)
    for i in range(1, a + 1):
        prefix_A[i] = prefix_A[i - 1] + candy_A[i - 1]
        max_A = max(max_A, prefix_A[i])

    # 2. 计算 B 的最大的前缀和
    max_B, prefix_B = 0, [0] * (b + 1)
    for i in range(1, b + 1):
        prefix_B[i] = prefix_B[i - 1] + candy_B[i - 1]
        max_B = max(max_B, prefix_B[i])

    print(max_A + max_B)