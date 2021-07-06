/**
 * @param {string} num
 * @return {number[]}
 */
var splitIntoFibonacci = function(num) {
    const maxInt = Math.pow(2, 31) - 1
    const res = []

    const dfs = (startIndex, prevTwoNumSum, prevNum) => {
        if (startIndex == num.length) {
            return res.length >= 3
        }

        let currNum = 0
        for (let i = startIndex; i < num.length; i++) {
            if (i > startIndex && num[startIndex] == '0') break
            currNum = currNum * 10 + (num[i].charCodeAt() - '0'.charCodeAt())
            if (currNum > maxInt) break

            if (res.length >= 2) {
                if (currNum < prevTwoNumSum) continue
                else if (currNum > prevTwoNumSum) break
            }
            res.push(currNum)
            if (dfs(i + 1, prevNum + currNum, currNum)) return true
            res.pop()
        }

        return false;
    }

    dfs(0, 0, 0)
    return res
};