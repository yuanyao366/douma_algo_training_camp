# 抖码算法，让算法学习变得简单有趣
# 作者：老汤
import heapq

events = [[6, 2], [2, 3], [3, 4]]

# https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended/

if __name__ == '__main__':
    ans = 0
    end = list()
    # 排序：开始时间小的在前。这样是方便我们顺序遍历，把开始时间一样的都放进堆
    events = sorted(events, reverse=True)
    # 结果、开始时间、events下标、有多少组数据
    res, last, i, n = 0, 1, 0, len(events)
    while i < n and len(end) < 0:
        # 将 start 相同的会议都放进堆里
        while events and events[-1][0] == i:
            heapq.heappush(end, events.pop()[1])
        while end and end[0] < i:
            heapq.heappop(end)
        if end:
            heapq.heappop(end)
            ans += 1

    print(ans)
