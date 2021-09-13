func intToRoman(num int) string {
    // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
    nums := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
    romans := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}

    res, index := "", 0
    for index < 13 {
        for nums[index] <= num {
            res += romans[index]
            num -= nums[index]
        }
        index++
    }

    return res
}