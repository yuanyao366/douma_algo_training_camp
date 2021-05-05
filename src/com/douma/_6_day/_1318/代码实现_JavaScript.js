var minFlips = function(a, b, c) {
    const aOrb = a | b
    const equal = aOrb ^ c
    if (equal == 0) return 0

    let ans = 0
    for (let i = 0; i < 31; i++) {
        const mask = 1 << i
        if ((equal & mask) > 0) {
            if ((a & mask) == (b & mask) && (c & mask) == 0) {
                ans += 2
            } else {
                ans += 1
            }
        }
    }
    return ans
};