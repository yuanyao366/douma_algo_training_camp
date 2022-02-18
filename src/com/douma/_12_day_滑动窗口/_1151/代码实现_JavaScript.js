/**
 * @param {number[]} data
 * @return {number}
 */
var minSwaps = function(data) {
    const INT_MAX = Math.pow(2, 31) - 1
    let k = 0;
    data.forEach(e => {
        if (e == 1) k++
    })

    let left = 0, right = 0
    let windowZeroCnt = 0, minZeroCnt = INT_MAX
    while (right < data.length) {
        if (data[right] == 0) windowZeroCnt++
        if (right - left + 1 == k) {
            minZeroCnt = Math.min(minZeroCnt, windowZeroCnt)
            if (data[left] == 0) windowZeroCnt--
            left++
        }
        right++
    }

    return minZeroCnt == INT_MAX ? 0 : minZeroCnt
};