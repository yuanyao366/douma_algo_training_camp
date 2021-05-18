/**
 * @param {character[][]} matrix
 * @return {number}
 */
var maximalRectangle = function(matrix) {
    const m = matrix.length
    if (m == 0) return 0
    const n = matrix[0].length

    const left = new Array(m).fill(0).map(() => new Array(n).fill(0))
    for (let row = 0; row < m; row++) {
        for (let col = 0; col < n; col++) {
            if (matrix[row][col] == '1') {
                const leftOnes = (col == 0 ? 0 : left[row][col - 1])
                left[row][col] = leftOnes + 1
            }
        }

    }

    let ans = 0
    for (let col = 0; col < n; col++) {
        const up = new Array(m).fill(0)
        const down = new Array(m).fill(m);

        const stack = []
        for (let row = 0; row < m; row++) {
            while (stack.length && left[row][col] <= left[stack[stack.length - 1]][col]) {
                down[stack[stack.length - 1]] = row
                stack.pop()
            }
            up[row] = (!stack.length ? -1 : stack[stack.length - 1])
            stack.push(row)
        }

        for (let row = 0; row < m; row++) {
            const height = left[row][col]
            const width = down[row] - up[row] - 1
            ans = Math.max(ans, height * width)
        }
    }

    return ans
};