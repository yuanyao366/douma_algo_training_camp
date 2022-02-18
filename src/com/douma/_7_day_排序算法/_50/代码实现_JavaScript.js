/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function(x, n) {
    if (n < 0) {
        x = 1 / x
        n = -n
    }
    let res = 1.0
    while (n) {
        if (n & 1) res *= x
        x *= x
        n >>= 1
    }
    return res
};

var myPow1 = function(x, n) {
    if (n < 0) {
        x = 1 / x
        n = -n
    }
    return quickMul(x, n)
};

var quickMul = function(x, n) {
    if (n == 0) return 1.0
    const y = quickMul(x, Math.floor(n / 2))
    return n % 2 == 0 ? y * y : y * y * x
}