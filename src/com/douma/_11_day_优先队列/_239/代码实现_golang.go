// 优先队列
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
var a []int
type hp struct{ sort.IntSlice }
func (h hp) Less(i, j int) bool  { return a[h.IntSlice[i]] > a[h.IntSlice[j]] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{}   { a := h.IntSlice; v := a[len(a)-1]; h.IntSlice = a[:len(a)-1]; return v }

func maxSlidingWindow(nums []int, k int) []int {
    a = nums
    // tips：这里维护的是大顶堆
    // 两个元素值不想等的话，那么元素大的放在前面，
    // 如果两个元素值相等的话，坐标大的放在前面，这样坐标 小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了，
    // 其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
    q := &hp{make([]int, k)}
    for i := 0; i < k; i++ {
        q.IntSlice[i] = i
    }
    heap.Init(q)

    n := len(nums)
    ans := make([]int, 1, n - k + 1)
    ans[0] = nums[q.IntSlice[0]]
    for i := k; i < n; i++ {
        heap.Push(q, i)
        for q.IntSlice[0] <= i - k {
            heap.Pop(q)
        }
        ans = append(ans, nums[q.IntSlice[0]])
    }
    return ans
}