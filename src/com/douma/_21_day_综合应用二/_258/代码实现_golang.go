func addDigits1(num int) int {
    for num >= 10 {
        num = totalSum(num)
    }
    return num
}

func totalSum(num int) int {
    var total = 0
    for num != 0 {
        total += num % 10
        num /= 10
    }
    return total
}


// 数学法
// 解释：https://blog.csdn.net/weixin_41541562/article/details/106635899
func addDigits(num int) int {
    return (num - 1) % 9 + 1
}