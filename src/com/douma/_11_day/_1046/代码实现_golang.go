// 排序解法
// 时间复杂度： O(n^2logn)
func lastStoneWeight1(stones []int) int {
    var n = len(stones)
    if n == 1 {
        return stones[0]
    }
    for i := 0; i < n - 1; i++ {
        sort.Ints(stones)
        var x, y = stones[n - 2], stones[n - 1]
        if x == 0 {
            break
        }
        stones[n - 1] = y - x
        stones[n - 2] = 0
    }
    return stones[n - 1]
}

type hp struct{ sort.IntSlice }
// 大顶堆
func (h hp) Less(i, j int) bool {
    return h.IntSlice[i] > h.IntSlice[j]
}
func (h *hp) Push(v interface{}) {
    h.IntSlice = append(h.IntSlice, v.(int))
}
func (h *hp) Pop() interface{} {
    a := h.IntSlice
    v := a[len(a)-1]
    h.IntSlice = a[:len(a)-1]
    return v
}
func (h *hp) push(v int) {
    heap.Push(h, v)
}
func (h *hp) pop() int {
    return heap.Pop(h).(int)
}

// 大顶堆
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
func lastStoneWeight(stones []int) int {
    var n = len(stones)
    if n == 1 {
        return stones[0]
    }
    pq := &hp{stones}
    heap.Init(pq)
    for pq.Len() > 1 {
        var x, y = pq.pop(), pq.pop()
        if x > y {
            pq.push(x - y)
        }
    }
    if pq.Len() == 0 {
        return 0
    } else {
        return pq.IntSlice[0]
    }
}