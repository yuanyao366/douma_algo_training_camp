var hammingDistance = function(x, y) {
    let diff = x ^ y, res = 0
    while (diff) {
        diff &= (diff - 1)
        res++
    }
    return res
};