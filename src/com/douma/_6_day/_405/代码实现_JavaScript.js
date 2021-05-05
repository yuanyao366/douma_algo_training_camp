var toHex = function(num) {
    if (num == 0) return "0"
    const hexChars = "0123456789abcdef"
    let res = ""
    while (num != 0) {
        res = hexChars[(num & 15)] + res
        num >>>= 4
    }
    return res
};