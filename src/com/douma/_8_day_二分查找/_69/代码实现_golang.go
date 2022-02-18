func mySqrt(x int) int {
    var left, right, ans = 0, x, -1
    for left <= right {
        var k = left + (right - left) / 2
        if k * k <= x {
            ans, left = k, k + 1
        } else {
            right = k - 1
        }
    }
    return ans
}