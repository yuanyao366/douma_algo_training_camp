# 抖码算法，让算法学习变得简单有趣
# 作者：小莹
import heapq

if __name__ == '__main__':
    # 1. 输入数据处理
    data = str(input()).split(" ")
    m, n = int(data[0]), int(data[1])
    task_times, data = [0] * n, str(input()).split(" ")
    for i in range(n):
        task_times[i] = int(data[i])

    # 2. 将所有任务放入到小顶堆
    # 这样可以很快的拿到时间最短的任务
    pq = task_times
    heapq.heapify(pq)

    # 3. 处理器并行处理任务
    # 定义一个 m 长度的数组，模拟 m 个并行的处理器
    # 然后不断将堆顶的任务并行放入到 m 个处理器中
    slots = [0] * m
    while len(pq) > 0:
        for i in range(m):
            if len(pq) > 0:
                slots[i] += heapq.heappop(pq)
            else:
                break

    # 4. 拿到所有处理器中，耗时最长的处理器，其耗时就是整个耗时
    max_time = max(slots)

    print(max_time)

