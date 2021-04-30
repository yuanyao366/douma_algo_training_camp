var reverse = function(x) {
    // 数值范围为 [−2^31,  2^31 − 1]
    const boundry = x > 0 ? 2147483647 : 2147483648
    let y = Math.abs(x)
    let res = 0
    while (y != 0) {
        res = res * 10 + y % 10;
        if (res > boundry) return 0
        y = Math.floor(y / 10)
    }
    return x > 0 ? res : -res
};