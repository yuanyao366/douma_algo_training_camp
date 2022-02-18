var myAtoi = function(s) {
    if (s.length == 0) return 0
    const n = s.length

    let i = 0
    while (i < n && s[i] == ' ') i++

    let sign = 1
    if (i < n && (s[i] == '+' || s[i] == '-')) {
        if (s[i] == '-') sign = -1
        i++
    }

    const INT_MAX = 2147483647
    const INT_MIN = -2147483648
    let base = 0
    while (i < n && s[i] >= '0' && s[i] <= '9') {
        if (base > Math.floor(INT_MAX / 10)
                || (base == Math.floor(INT_MAX / 10) && s[i] - '0' > 7)) {
            if (sign == 1) return INT_MAX
            else return INT_MIN
        }
        base = base * 10 + (s[i++] - '0')
    }

    return sign * base
};