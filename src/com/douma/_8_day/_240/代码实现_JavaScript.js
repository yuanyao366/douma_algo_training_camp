/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function(matrix, target) {
    const m = matrix.length, n = matrix[0].length
    let row = m - 1, col = 0
    while (row >= 0 && col < n) {
        if (matrix[row][col] == target) return true
        else if (matrix[row][col] < target) col++
        else row--
    }
    return false;
};