// 0 <= left <= right <= 2^31 - 1
// 超时
func rangeBitwiseAnd1(left int, right int) int {
    ans := left
    for i := left + 1; i <= right; i++ {
        ans &= i
    }
    return ans
}

func rangeBitwiseAnd2(left int, right int) int {
    shift := 0
    for left < right {
        left >>= 1
        right >>= 1
        shift++
    }
    return left << shift
}

func rangeBitwiseAnd(left int, right int) int {
    for left < right {
        right = right & (right - 1)
    }
    return right
}