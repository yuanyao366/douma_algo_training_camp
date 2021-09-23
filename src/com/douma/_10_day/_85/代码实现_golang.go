func maximalRectangle(matrix [][]byte) int {
    var m = len(matrix)
    if m == 0 {
        return 0
    }
    var n = len(matrix[0])

    var left = make([][]int, m)
    for row := 0; row < m; row++ {
        left[row] = make([]int, n)
        for col := 0; col < n; col++ {
            if matrix[row][col] == '1' {
                if col == 0 {
                    left[row][col] = 1
                } else {
                    left[row][col] = left[row][col - 1] + 1
                }
            }
        }
    }

    var ans = 0
    for col := 0; col < n; col++ {
        var up, down = make([]int, m), make([]int, m)
        var stack = []int{}
        for row := 0; row < m; row++ {
            down[row] = m
            for len(stack) > 0 && left[row][col] <= left[stack[len(stack) - 1]][col] {
                down[stack[len(stack) - 1]] = row
                stack = stack[:len(stack) - 1]
            }
            if len(stack) == 0 {
                up[row] = -1
            } else {
                up[row] = stack[len(stack) - 1]
            }
            stack = append(stack, row)
        }

        for row := 0; row < m; row++ {
            var height = left[row][col]
            var width = down[row] - up[row] - 1
            var area = height * width
            if area > ans {
                ans = area
            }
        }
    }

    return ans
}