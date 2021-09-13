func transpose(matrix [][]int) [][]int {
    m, n := len(matrix), len(matrix[0])

    t := make([][]int, n)
    for i := range t {
        t[i] = make([]int, m)
    }

    for i := 0; i < m; i++ {
        for j := 0; j < n; j++ {
            t[j][i] = matrix[i][j]
        }
    }

    return t
}