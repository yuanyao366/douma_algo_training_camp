type MedianFinder struct {
    queMin, queMax hp
}

func Constructor() MedianFinder {
    return MedianFinder{}
}

func (mf *MedianFinder) AddNum(num int) {
    minHeap, maxHeap := &mf.queMin, &mf.queMax
    if maxHeap.Len() == 0 {
        heap.Push(maxHeap, num)
        return 
    }
    if num <= maxHeap.IntSlice[0] {
        // 存储正数，用来表示大顶堆
        heap.Push(maxHeap, num)
    } else {
        // 存储正数，用来表示小顶堆
        heap.Push(minHeap, -num)
    }

    if maxHeap.Len() > minHeap.Len() + 1 {
        heap.Push(minHeap, -heap.Pop(maxHeap).(int))
    }
    if maxHeap.Len() < minHeap.Len() {
        heap.Push(maxHeap, -heap.Pop(minHeap).(int))
    }
}

func (mf *MedianFinder) FindMedian() float64 {
    minHeap, maxHeap := mf.queMin, mf.queMax
    if maxHeap.Len() > minHeap.Len()  {
        return float64(maxHeap.IntSlice[0])
    } else {
        return float64(maxHeap.IntSlice[0] - minHeap.IntSlice[0]) * 0.5
    }
}

type hp struct{ sort.IntSlice }

// 大顶堆
func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{}   { a := h.IntSlice; v := a[len(a)-1]; h.IntSlice = a[:len(a)-1]; return v }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */