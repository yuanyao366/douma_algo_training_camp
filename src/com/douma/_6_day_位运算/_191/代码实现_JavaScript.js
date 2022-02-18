var hammingWeight = function(n) {
    let res = 0
    while (n) {
        n &= (n - 1)
        res++
    }
    return res
};
var hammingWeight2 = function(n) {
    let res = 0
    for (let i = 0; i < 32; i++) {
        res += n & 1
        n >>= 1
    }
    return res
};
var hammingWeight1 = function(n) {
    let res = 0
    for (let i = 0; i < 32; i++) {
        if ((n & (1 << i)) != 0) res ++
    }
    return res
};