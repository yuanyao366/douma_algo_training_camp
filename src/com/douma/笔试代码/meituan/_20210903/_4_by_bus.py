# 抖码算法，让算法学习变得简单有趣
# 作者：菲菲

# 因为 n 和 m 的最大值是 500，而且 n + m 最大也为 500
# 所以下面的时间复杂度是可以接受的
# 时间复杂度：O(n(n + m))
# 空间复杂度：O(n^2)
from collections import deque

if __name__ == '__main__':
    data = str(input()).split(" ")
    n, m = int(data[0]), int(data[1])

    # 记录每一条公交线上的公交站，用于后面的建图
    bus_stations = [None] * (m + 1)
    for i in range(m + 1):
        bus_stations[i] = []
    # 时间复杂度：O(nm)
    for i in range(n):
        data = str(input()).split(" ")
        k = int(data[0])
        for j in range(0, k):
            bus_stations[int(data[j + 1])].append(i)

    # 根据每一条公交线上的公交站，建图
    graph = [None] * n
    for v in range(n):
        graph[v] = []
    # 时间复杂度：O(n^2)
    for adj in bus_stations:
        # 每条公交线上的公交站都是相互直接连接的
        for v in adj:
            for w in adj:
                if v != w:
                    graph[v].append(w)

    res = [["0"] * n for _ in range(n)]
    # 依次从每个公交站开始，进行 BFS ，可以计算这个公交站到每一个其他公交站的最短路径
    # 时间复杂度：O(n^2)
    # 空间复杂度：O(n^2)
    for v in range(n):
        queue = deque()
        queue.append(v)
        visited = [False] * n
        visited[v] = True
        shortest_path = 0
        while len(queue):
            size = len(queue)
            for _ in range(size):
                next = queue.popleft()
                res[v][next] = str(shortest_path)
                for w in graph[next]:
                    if not visited[w]:
                        queue.append(w)
                        visited[w] = True
            shortest_path += 1

    # 打印结果
    for i in range(n):
        print(" ".join(res[i]))
