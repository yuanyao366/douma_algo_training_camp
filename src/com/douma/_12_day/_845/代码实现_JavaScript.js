/**
 * @param {number[]} arr
 * @return {number}
 */
var longestMountain = function(arr) {
    const n = arr.length
    let ans = 0, left = 0
    while (left + 2 < n) {
        let right = left + 1
        if (arr[left] < arr[right]) {
            while (right + 1 < n && arr[right] < arr[right + 1]) right++
            if (right + 1 < n && arr[right] > arr[right + 1]) {
                while (right + 1 < n && arr[right] > arr[right + 1]) right++
                ans = Math.max(ans, right - left + 1)
            } else {
                right++
            }
        }
        left = right
    }
    return ans
};