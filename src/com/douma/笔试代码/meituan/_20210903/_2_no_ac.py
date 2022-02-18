# 抖码算法，让算法学习变得简单有趣
# 作者：老汤


if __name__ == '__main__':
    n, acs = int(input()), str(input())

    c_count = res = 0
    for i in range(n - 1, -1, -1):
        if acs[i] == 'c':
            c_count += 1
        else:
            res += c_count

    print(res)
