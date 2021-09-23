// 按照欧几里得距离排序
func kClosest1(points [][]int, k int) [][]int {
    sort.Slice(points, func(i, j int) bool {
        var point1 = points[i]
        var point2 = points[j]
        return point1[0] * point1[0] + point1[1] * point1[1] < (point2[0] * point2[0] + point2[1] * point2[1])
    })
    return points[0:k]
}


type pair struct {
    dist  int
    point []int
}
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].dist > h[j].dist }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

// 大顶堆
func kClosest(points [][]int, k int) (ans [][]int) {
    h := make(hp, k)
    for i, p := range points[:k] {
        h[i] = pair{p[0]*p[0] + p[1]*p[1], p}
    }
    heap.Init(&h) // O(k) 初始化堆
    for _, p := range points[k:] {
        if dist := p[0]*p[0] + p[1]*p[1]; dist < h[0].dist {
            h[0] = pair{dist, p}
            heap.Fix(&h, 0) // 效率比 pop 后 push 要快
        }
    }
    for _, p := range h {
        ans = append(ans, p.point)
    }
    return
}
