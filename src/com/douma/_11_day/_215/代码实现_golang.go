// 排序解法
// 时间复杂度：O(nlogn)
// 空间复杂度：快排 O(logn)，归并排序 O(n)
func findKthLargest1(nums []int, k int) int {
    sort.Ints(nums)
    return nums[len(nums) - k]
}


type hp struct{ sort.IntSlice }
// 小顶堆
func (h hp) Less(i, j int) bool {
    return h.IntSlice[i] < h.IntSlice[j]
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

// 小顶堆
// 时间复杂度：O(nlogk)
// 空间复杂度：O(k)
func findKthLargest2(nums []int, k int) int {
    var pq = &hp{}
    for i := range nums {
        pq.push(nums[i])
        if pq.Len() > k {
            pq.pop()
        }
    }
    return pq.pop()
}

// 快速排序分区优化
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func findKthLargest(nums []int, k int) int {
    var left, right = 0, len(nums) - 1
    var target = len(nums) - k
    for true {
        var pivotIndex = partition(nums, left, right)
        if pivotIndex == target {
            return nums[pivotIndex]
        } else if pivotIndex < target {
            left = pivotIndex + 1
        } else {
            right = pivotIndex - 1
        }
    }
    return -1
}

// 二分切分
func partition(nums []int, lo int, hi int) int {
    i := rand.Intn(hi - lo + 1) + lo
    nums[i], nums[hi] = nums[hi], nums[i]
    pivot := nums[hi]
    less, great := lo, lo
    for ; great <= hi - 1; great++ {
        if nums[great] < pivot {
            nums[less], nums[great] = nums[great], nums[less]
            less++
        }
    }
    nums[less], nums[hi] = nums[hi], nums[less]
    return less
}