/**
 * @param {number} num
 * @return {number}
 */
var addDigits = function(num) {
    const totalSum = (num) => {
        let total = 0
        while (num) {
            total += num % 10
            num = Math.floor(num / 10)
        }
        return total
    }

    while (num >= 10) num = totalSum(num)

    return num
};