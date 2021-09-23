
type IHeap [][2]int

func (h IHeap) Len() int           { return len(h) }
func (h IHeap) Less(i, j int) bool { return h[i][1] < h[j][1] }
func (h IHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IHeap) Push(x interface{}) {
    *h = append(*h, x.([2]int))
}

func (h *IHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}

// 小顶堆
// 时间复杂度：O(nlogk)
// 空间复杂度：O(n)
func topKFrequent1(nums []int, k int) []int {
    var count = make(map[int]int)
    for _, num := range nums {
        count[num]++
    }

    var pq = &IHeap{}
    heap.Init(pq)

    for key, value := range count {
        heap.Push(pq, [2]int{key, value})
        if pq.Len() > k {
            heap.Pop(pq)
        }
    }

    ret := make([]int, k)
    for i := 0; i < k; i++ {
        ret[k - i - 1] = heap.Pop(pq).([2]int)[0]
    }
    return ret
}


// 快速排序分区优化
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func topKFrequent(nums []int, k int) []int {
    var count = make(map[int]int)
    for _, num := range nums {
        count[num]++
    }

    var uniqueNums, index = make([]int, len(count)), 0
    for key, _ := range count {
        uniqueNums[index] = key
        index++
    }

    var left, right = 0, len(uniqueNums) - 1
    var target = len(uniqueNums) - k
    for true {
        var pivotIndex = partition(uniqueNums, left, right, count)
        if pivotIndex == target {
            break
        } else if pivotIndex < target {
            left = pivotIndex + 1
        } else {
            right = pivotIndex - 1
        }
    }

    return uniqueNums[len(uniqueNums) - k:len(uniqueNums)]
}

func partition(nums []int, lo int, hi int, count map[int]int) int {
    i := rand.Intn(hi - lo + 1) + lo
    nums[i], nums[hi] = nums[hi], nums[i]
    pivot := count[nums[hi]]
    less, great := lo, lo
    for ; great <= hi - 1; great++ {
        if count[nums[great]] < pivot {
            nums[less], nums[great] = nums[great], nums[less]
            less++
        }
    }
    nums[less], nums[hi] = nums[hi], nums[less]
    return less
}