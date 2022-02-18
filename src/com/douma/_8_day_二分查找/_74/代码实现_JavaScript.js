/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function(matrix, target) {
    const m = matrix.length, n = matrix[0].length
    let left = 0, right = m * n - 1
    while (left <= right) {
        const mid = Math.floor((left + right) / 2)
        const num = matrix[Math.floor(mid / n)][mid % n]
        if (num == target) return true
        else if (num < target) left = mid + 1
        else right = mid - 1
    }
    return false
};