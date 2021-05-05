var divide = function(dividend, divisor) {
    if (dividend == -2147483648 && divisor == -1) return 2147483647
    let a = Math.abs(dividend), b = Math.abs(divisor), res = 0
    const sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1
    for (let x = 31; x >= 0; x--) {
        if ((a >>> x) - b >= 0) {
            a = a - (b << x)
            res = res + (1 << x)
        }
    }
    if (res == -2147483648) return -2147483648
    return sign * res
};