/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function(digits) {
    if (!digits) return []

    const phone = new Map([
        ['2', "abc"],
        ['3', "def"],
        ['4', "ghi"],
        ['5', "jkl"],
        ['6', "mno"],
        ['7', "pqrs"],
        ['8', "tuv"],
        ['9', "wxyz"]
    ])

    res = []
    const findCombination = (index, combination) => {
        if (index == digits.length) {
            res.push(combination.slice())
            return
        }

        for (const c of phone.get(digits[index])) {
            findCombination(index + 1, combination + c)
        }
    }

    findCombination(0, "")
    return res
};