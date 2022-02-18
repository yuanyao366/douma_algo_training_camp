var multiply = function(num1, num2) {
    if (num1 == "0" || num2 == "0") return "0"
    const m = num1.length, n = num2.length
    const res = new Array(m + n).fill(0)
    for (let i = m - 1; i >= 0; i--) {
        const x = num1[i] - '0'
        for (let j = n - 1; j >= 0; j--) {
            const y = num2[j] - '0'
            const sum = res[i + j + 1] + x * y
            res[i + j + 1] = sum % 10
            res[i + j] += Math.floor(sum / 10)
        }
    }
    const index = res[0] == 0 ? 1 : 0
    return res.slice(index).join("")
};