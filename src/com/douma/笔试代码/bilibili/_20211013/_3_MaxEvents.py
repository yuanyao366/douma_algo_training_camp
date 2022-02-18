# 抖码算法，让算法学习变得简单有趣
# 作者：老汤
import heapq

if __name__ == '__main__':
    # 输入：[[1,2],[2,3],[3,4]]
    # 得到： [1,2],[2,3],[3,4]
    data = str(input())[1:-1].split(",")
    n = len(data) // 2
    events = [[0] * 2 for _ in range(n)]
    for i in range(n):
        events[i][0] = int(data[i * 2][1:])
        events[i][1] = int(data[i * 2 + 1][0:-1])

    # 按照开始时间升序排列，这样，对于相同开始时间的会议，可以一起处理
    events.sort(key=lambda x: x[0])

    # 小顶堆：用于高效的维护最小的 endDay
    pq = []
    res, curr_day, i = 0, 1, 0
    while i < n or len(pq):
        # 将所有开始时间等于 currDay 的会议的结束时间放到小顶堆
        while i < n and events[i][0] == curr_day:
            heapq.heappush(pq, events[i][1])
            i += 1
        # 将已经结束的会议弹出堆中，即堆顶的结束时间小于 currDay 的
        while len(pq) and pq[0] < curr_day:
            heapq.heappop(pq)
        # 贪心的选择结束时间最小的会议参加
        if len(pq):
            # 参加的会议，就从堆中删除
            heapq.heappop(pq)
            res += 1
        # 当前的天往后走一天，开始看下下一天能不能参加会议
        curr_day += 1

    print(res)
