/**
 * @param {number} n
 * @return {number}
 */
var monotoneIncreasingDigits = function(n) {
    const strN = n.toString().split("")
    let i = 1
    while (i < strN.length && strN[i - 1] <= strN[i]) {
        i++
    }

    if (i < strN.length) {
        while (i > 0 && strN[i - 1] > strN[i]) {
            strN[i - 1] -= 1
            i--
        }
        i++
        while (i < strN.length) strN[i++] = '9'
    }

    return parseInt(strN.join(""))
};