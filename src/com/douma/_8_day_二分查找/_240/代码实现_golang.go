// 暴力解法
// 时间复杂度 O(mn)
// 空间复杂度 O(1)
func searchMatrix1(matrix [][]int, target int) bool {
    var m, n = len(matrix), len(matrix[0])
    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            if matrix[i][j] == target {
                return true
            }
        }
    }
    return false
}

// 二分查找
// 时间复杂度 O(min(m, n) * max(logm, logn))
// 空间复杂度 O(1)
func searchMatrix2(matrix [][]int, target int) bool {
    var m, n = len(matrix), len(matrix[0])
    var shortDim = min(m, n)
    for i := 0; i < shortDim; i++ {
        var rowFound = binarySearchRow(matrix, i, target)
        var colFount = binarySearchCol(matrix, i, target)
        if rowFound || colFount {
            return true
        }
    }
    return false
}

func min(a, b int) int {
    if a > b {
        return b
    }
    return a
}

func binarySearchRow(matrix [][]int, row int, target int) bool {
    var left, right = row, len(matrix[0]) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if matrix[row][mid] == target {
            return true
        } else if matrix[row][mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return false
}

func binarySearchCol(matrix [][]int, col int, target int) bool {
    var left, right = col, len(matrix) - 1
    for left <= right {
        var mid = left + (right - left) / 2
        if matrix[mid][col] == target {
            return true
        } else if matrix[mid][col] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return false
}

// 缩减搜索空间
// 时间复杂度 O(m + n)
// 空间复杂度 O(1)
func searchMatrix(matrix [][]int, target int) bool {
    var m, n = len(matrix), len(matrix[0])
    var i, j = m - 1, 0
    for i >= 0 && j < n {
        if matrix[i][j] < target {
            j++
        } else if matrix[i][j] > target {
            i--
        } else {
            return true
        }
    }
    return false
}