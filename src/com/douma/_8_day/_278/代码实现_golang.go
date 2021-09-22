/**
 * Forward declaration of isBadVersion API.
 * @param   version   your guess about first bad version
 * @return 	 	      true if current version is bad
 *			          false if current version is good
 * func isBadVersion(version int) bool;
 */

// 思路一：在循环体内查找目标值
func firstBadVersion1(n int) int {
    var left, right = 1, n
    for left <= right {
        var mid = left + (right - left) / 2
        if isBadVersion(mid) {
            if mid == 1 || !isBadVersion(mid - 1) {
                return mid
            } else {
                right = mid - 1
            }
        } else {
            left = mid + 1
        }
    }
    return -1
}

// 思路二：在循环体内排除没有目标值的区间
func firstBadVersion(n int) int {
    var left, right = 1, n
    for left < right {
        var mid = left + (right - left) / 2
        if isBadVersion(mid) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    if isBadVersion(left) {
        return left
    } else {
        return -1
    }
}