func searchMatrix(matrix [][]int, target int) bool {
    var m, n = len(matrix), len(matrix[0])
    var left, right = 0, m * n - 1
    for left <= right {
        var mid = left + (right - left) / 2
        var num = matrix[mid / n][mid % n]
        if num == target {
            return true
        } else if num < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return false
}