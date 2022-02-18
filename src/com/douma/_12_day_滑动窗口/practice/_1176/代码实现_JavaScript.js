/**
 * @param {number[]} calories
 * @param {number} k
 * @param {number} lower
 * @param {number} upper
 * @return {number}
 */
var dietPlanPerformance = function(calories, k, lower, upper) {
    let left = 0, right = 0
    let totalScore = 0, windowTotalCal = 0
    while (right < calories.length) {
        windowTotalCal += calories[right]
        if (right -left + 1 == k) {
            if (windowTotalCal < lower) {
                totalScore--
            } else if (windowTotalCal > upper) {
                totalScore++
            }
            windowTotalCal -= calories[left]
            left++
        }
        right++
    }
    return totalScore
};