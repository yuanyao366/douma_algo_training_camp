// 动态规划
func wiggleMaxLength1(nums []int) int {
    var n = len(nums)
    if n < 2 {
        return n
    }

    var up = make([]int, n)
    var down = make([]int, n)

    up[0], down[0] = 1, 1

    for i := 1; i < n; i++ {
        if nums[i] > nums[i - 1] {
            up[i] = down[i - 1] + 1;
            down[i] = down[i - 1];
        } else if nums[i] < nums[i - 1] {
            up[i] = up[i - 1];
            down[i] = up[i - 1] + 1;
        } else {
            up[i] = up[i - 1];
            down[i] = down[i - 1];
        }
    }
    if up[n - 1] > down[n - 1] {
        return up[n - 1]
    } else {
        return down[n - 1]
    }
}

// 动态规划 + 状态压缩
func wiggleMaxLength(nums []int) int {
    var n = len(nums)
    if n < 2 {
        return n
    }

    var up, down = 1, 1

    for i := 1; i < n; i++ {
        if nums[i] > nums[i - 1] {
            up = down + 1
        } else if nums[i] < nums[i - 1] {
            down = up + 1
        }
    }
    if up > down {
        return up
    } else {
        return down
    }
}