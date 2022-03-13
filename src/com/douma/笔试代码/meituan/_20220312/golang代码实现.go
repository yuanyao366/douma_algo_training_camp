package main
import (
    "fmt"
)

func main(){

    var n int
    fmt.Scanf("%d", &n)

    nums := make([]int, n)
    for i := 0; i < n; i++ {
        var num int
        fmt.Scanf("%d", &num)
        nums[i] = num
    }

    // dp1[i] 表示以 nums[i] 结尾的乘积等于 1 的子数组个数
    dp1 := make([]int, n)
    // dp2[i] 表示以 nums[i] 结尾的乘积等于 -1 的子数组个数
    dp2 := make([]int, n)

    if nums[0] == 1 {
        dp1[0] = 1
    } else {
        dp2[0] = 1
    }

    for i := 1; i < n; i++ {
        if nums[i] == 1 {
            // 因为 1 × 1 = 1，所以：
            // 以 nums[i] 结尾的乘积等于 1 的子数组个数 等于：
            // 以 nums[i - 1] 结尾的乘积等于 1 的子数组个数，再加上 1
            // 因为这个时候多了 1 个子数组，就是 [nums[i]] 本身
            dp1[i] = dp1[i - 1] + 1
            // 因为 1 × -1 = -1，所以以 nums[i] 结尾的乘积等于 -1 的子数组个数
            // 还是等于以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数
            dp2[i] = dp2[i - 1]
        } else {
            // 因为 -1 × -1 = 1，所以：
            // 以 nums[i] 结尾的乘积等于 1 的子数组个数 等于：
            // 以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数
            dp1[i] = dp2[i - 1]
            // 因为 -1 × 1 = -1，所以：
            // 以 nums[i] 结尾的乘积等于 -1 的子数组个数 等于：
            // 以 nums[i - 1] 结尾的乘积等于 -1 的子数组个数，再加上 1
            // 因为这个时候多了 1 个子数组，就是 [nums[i]] 本身
            dp2[i] = dp1[i - 1] + 1
        }
    }
    // 最终将每一个以 nums[i] 结尾的乘积等于 1 的子数组个数累加即得到结果
    ans := 0
    for i := 0; i < n; i++ {
        ans += dp1[i]
    }
    fmt.Printf("%d", ans)
}

func main2(){

    var n int
    fmt.Scanf("%d", &n)

    nums := make([]int, n)
    for i := 0; i < n; i++ {
        var num int
        fmt.Scanf("%d", &num)
        nums[i] = num
    }

    // 前缀积
    // prefixProduct[i] 表示数组 nums 前 i 个元素，i 从 0 开始
    prefixProduct := make([]int, n + 1)
    // 前 0 个元素表示没有元素，因为求乘积，所以赋值为 1
    prefixProduct[0] = 1
    for i := 0; i < n; i++ {
        prefixProduct[i + 1] = prefixProduct[i] * nums[i]
    }

    ans := 0
    for i := 0; i < n; i++ {
        for j := 0; j <= i; j++ {

            // 求区间 [j...i] 的乘积
            tmp := prefixProduct[i] / prefixProduct[j - 1]
            if tmp == 1 {
                ans++
            }
        }
    }

    fmt.Printf("%d", ans)
}

func main1(){

    var n int
    fmt.Scanf("%d", &n)

    nums := make([]int, n)
    for i := 0; i < n; i++ {
        var num int
        fmt.Scanf("%d", &num)
        nums[i] = num
    }

    ans := 0
    for i := 0; i < n; i++ {
        for j := 0; j <= i; j++ {
            tmp := 1
            // 求区间 [j...i] 的乘积
            for i := 0; i < n; i++ {
                tmp *= nums[k]
            }
            if tmp == 1 {
                ans++
            }
        }
    }

    fmt.Printf("%d", ans)
}