// 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func majorityElement1(nums []int) int {
    var cntMap = make(map[int]int)
    for _, num := range nums {
        cntMap[num]++
        if cntMap[num] > len(nums) / 2 {
            return num
        }
    }
    return -1
}

// 排序查找
// 时间复杂度：O(nlogn)
// 空间复杂度：O(logn) 或者 O(n)
func majorityElement2(nums []int) int {
    sort.Ints(nums)
    return nums[len(nums) / 2]
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

// 堆查找
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
func majorityElement3(nums []int) int {
    // 查找第 k 小元素
    // 大顶堆
    var pq = &hp{}
    var k = len(nums) / 2 + 1
    for i := range nums {
        pq.push(nums[i])
        if pq.Len() > k {
            pq.pop()
        }
    }
    return pq.IntSlice[0]
}


// 快速排序分区优化
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func majorityElement4(nums []int) int {
    var k = len(nums) / 2 + 1
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

// 分治
// 时间复杂度：O(nlogn)
// 空间复杂度：O(logn)
func majorityElement5(nums []int) int {
    return majorityElementR(nums, 0, len(nums) - 1)
}

func majorityElementR(nums []int, lo int, hi int) int {
    if lo == hi {
        return nums[lo]
    }

    var mid = lo + (hi - lo) / 2
    var leftNum = majorityElementR(nums, lo, mid)
    var rightNum = majorityElementR(nums, mid + 1, hi)

    if leftNum == rightNum {
        return leftNum
    }

    var leftNumCnt = countInRange(nums, leftNum, lo, hi)
    var rightNumCnt = countInRange(nums, rightNum, lo, hi)
    if leftNumCnt > rightNumCnt {
        return leftNum
    } else {
        return rightNum
    }
}

func countInRange(nums []int, num int, lo int, hi int) int {
    var count = 0
    for i := lo; i <= hi; i++ {
        if nums[i] == num {
            count++
        }
    }
    return count
}

// Boyer-Moore 投票算法
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func majorityElement(nums []int) int {
    var candidate, count = -1, 0
    for _, num := range nums {
        if num == candidate {
            count++
        } else if count == 0 {
            candidate = num
            count++
        } else {
            count--
        }
    }
    return candidate
}