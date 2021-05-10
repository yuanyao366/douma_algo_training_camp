/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
    let left = 0, right = x, ans = -1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        if (mid * mid <= x) {
            ans = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return ans
};