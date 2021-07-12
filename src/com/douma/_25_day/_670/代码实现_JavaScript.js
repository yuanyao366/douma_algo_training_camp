/**
 * @param {number} num
 * @return {number}
 */
var maximumSwap = function(num) {
    // 计算每位上数字出现的最后索引位置
    const chars = num.toString().split("")
    const last = new Array(26).fill(0)
    for (let i = 0; i < chars.length; i++) {
        last[chars[i].charCodeAt() - '0'.charCodeAt()] = i
    }

    // 从高位到低位遍历
    // 对于每一位，如果后面有比这一位大的数字，则交换，然后返回
    for (let i = 0; i < chars.length; i++) {
        for (let d = 9; d > chars[i].charCodeAt() - '0'.charCodeAt(); d--) {
            if (last[d] > i) {
                [chars[i], chars[last[d]]] = [chars[last[d]], chars[i]]
                return parseInt(chars.join(""))
            }
        }
    }

    return num
};