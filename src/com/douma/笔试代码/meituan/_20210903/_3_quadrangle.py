

# G: graph G
# length: 环的长度
# path: 路径中的顶点
def dfs(G, target_path_len, path):
    res, last = 0, path[-1]

    # 只剩最后一步
    if len(path) == target_path_len - 1:
        # 在上一步所到的顶点的相邻顶点中寻找是否可以到达起始点
        for v in G[last]:
            if (v > path[1]) and (v not in path) and (path[0] in G[v]):
                # print(path + [v])
                res += 1
                return res
    # 在上一步所到的顶点的相邻顶点中，选择下一步的顶点
    for v in G[last]:
        if (v > path[0]) and (v not in path):
            # 注意这里的 path 早回溯的时候是不变的，和 str 一样
            res += dfs(G, target_path_len, path + [v])

    return res


# Graph G
n = int(input())
G = [0] * n
for i in range(n):
    G[i] = set()

for i in range(n):
    data = str(input()).split(" ")
    for j in range(len(data)):
        if data[j] == "1":
            G[i].add(j)

cnt = 0
# 分别从每个顶点开始回溯走 4 步
# 这里 n - 3 是一个优化：比如 n = 6 的话，那么就不用从顶点 3 开始选了
# 因为从顶点 3 开始，后面只有 4 和 5 这两个顶点了，不可能组成四边形的
for i in range(0, n - 3):
    cnt += dfs(G, 4, [i])
print(cnt)
