func letterCombinations(digits string) []string {
    var phone = map[byte]string {
        '2': "abc",
        '3': "def",
        '4': "ghi",
        '5': "jkl",
        '6': "mno",
        '7': "pqrs",
        '8': "tuv",
        '9': "wxyz",
    }

    var res = make([]string, 0)
    if len(digits) == 0 {
        return res
    }

    var findCombination func(int, string)
    findCombination = func(index int, combination string) {
        if len(digits) == index {
            res = append(res, combination)
            return
        }
        var numChar = digits[index]
        var letters = phone[numChar]
        for i := range letters {
            findCombination(index + 1, combination + string(letters[i]))
        }
    }

    findCombination(0, "")
    return res

}