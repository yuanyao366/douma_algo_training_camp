// 方法一：直接模拟
// 时间复杂度：O(n + k)
// 空间复杂度：O(1)
func findKthPositive1(arr []int, k int) int {
    var currNum, missCnt, lastMissNum, i = 1, 0, -1, 0
    for missCnt < k {
        if currNum == arr[i] {
            // bug 修复：注意 i 的边界
            if i + 1 < len(arr) {
                i++
            }
        } else {
            missCnt++
            lastMissNum = currNum
        }
        currNum++
    }
    return lastMissNum
}

// 方法二：二分查找
// 时间复杂度：O(logn)
// 空间复杂度：O(1)
// 元素 a[i] 之前缺失的正整数的个数为：a[i] - i - 1
func findKthPositive(arr []int, k int) int {
    if arr[0] > k {
        return k
    }
    /*
    解释：right 为什么是 arr.length 而非 arr.length - 1
    因为这里你需要考虑缺失的数字可能是数组最后一个元素的下一个整数，
    所以，这里进行二分的时候，范围必须是 [0....nums.length] 中查找
    比如：[1, 2, 3, 4, 5]  k = 1，
    那么这个时候第 1 个缺失的数字是 6 ，是数组最后一个元素的下一个整数

    就是说你要查找的数字有可能超出数组的范围
        */
    var left, right = 0, len(arr)
    for left < right {
        var mid = left + (right - left) / 2
        if arr[mid] - mid -  1 < k {
            left = mid + 1
        } else {
            right = mid
        }
    }
    var leftMissCnt = arr[left - 1] - (left - 1) - 1
    return arr[left - 1] + (k - leftMissCnt)
}