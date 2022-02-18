// 滑动窗口 + 桶
// 时间复杂度：O(n)
// 空间复杂度：O(min(n, k))
func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
    var left, right = 0, 0
    var bucketSize = int64(t + 1)

    // key 是 bucket_id
    // value 对应桶中的元素值
    // bug 修复：桶内存储的元素值也需要是 long 类型的
    // 要不然下面的 x - window.get(leftBucketId) <= t 会越界
    var window = make(map[int64]int64)

    for right < len(nums) {
        // 窗口内区间查找
        var x = int64(nums[right])
        var bucketId = getBucketId(x, bucketSize)

        // 一个桶中有多于 1 个元素值存在
        if _, has := window[bucketId]; has {
            return true
        }

        // 判断相邻的桶中是否存在符合条件的原始值
        var leftBucketId, rightBucketId = bucketId - 1, bucketId + 1
        var value, has = window[leftBucketId] 
        if has && x - value <= int64(t) {
            return true
        }
        value, has = window[rightBucketId] 
        if has && value - x <= int64(t) {
            return true
        }

        window[bucketId] = x

        // 维护桶的个数，桶的个数最多为 k 个，超过 k 个，我们就删除左边的桶
        if len(window) > k {
            delete(window, getBucketId(int64(nums[left]), bucketSize))
            left++
        }

        right++
    }

    return false
}

func getBucketId(x int64, bucketSize int64) int64 {
    if x >= 0 {
        return x / bucketSize
    }
    return (x + 1) / bucketSize - 1
}