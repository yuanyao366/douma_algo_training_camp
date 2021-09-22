/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * type MountainArray struct {
 * }
 *
 * func (this *MountainArray) get(index int) int {}
 * func (this *MountainArray) length() int {}
 */

func findInMountainArray(target int, mountainArr *MountainArray) int {
    // 1. 找到峰顶元素索引
    var peakIndex = searchPeakIndex(mountainArr)
    // 2. 在前半部分应用二分查找算法查找目标值
    var index = binarySearchFrontPart(mountainArr, 0, peakIndex, target)
    if index != -1 {
        return index
    }
    // 3. 在后半部分应用二分查找算法查找目标值
    return binarySearchLatterPart(mountainArr, peakIndex, mountainArr.length() - 1, target)
}

// 1. 找到峰顶元素索引
func searchPeakIndex(mountainArr *MountainArray) int {
    var left, right = 0, mountainArr.length() - 1
    for left < right {
        var mid = left + (right - left) / 2
        if mountainArr.get(mid) < mountainArr.get(mid + 1) { // 上坡
            left = mid + 1
        } else {
            right = mid
        }
    }
    return left
}

// 2. 在前半部分应用二分查找算法查找目标值（思路 2 实现）
func binarySearchFrontPart(mountainArr *MountainArray, left int, peakIndex int, target int) int {
    for left < peakIndex {
        var mid = left + (peakIndex - left) / 2
        if target > mountainArr.get(mid) {
            left = mid + 1
        } else {
            peakIndex = mid
        }
    }
    if mountainArr.get(left) == target {
        return left
    }
    return -1
}

// 3. 在后半部分应用二分查找算法查找目标值（思路 2 实现）
func binarySearchLatterPart(mountainArr *MountainArray, peakIndex int, right int, target int) int {
    for peakIndex < right {
        var mid = peakIndex + (right - peakIndex) / 2
        if target < mountainArr.get(mid) {
            peakIndex = mid + 1
        } else {
            right = mid
        }
    }
    if mountainArr.get(peakIndex) == target {
        return peakIndex
    }
    return -1
}









