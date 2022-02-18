// 方法一：暴力解法
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
func smallerNumbersThanCurrent1(nums []int) []int {
    var n = len(nums)
    var ret = make([]int, n)
    for i := 0; i < n; i++ {
        var cnt = 0
        for j := 0; j < n; j++ {
            if nums[j] < nums[i] {
                cnt++
            }
        }
        ret[i] = cnt
    }
    return ret
}


// 方法二：排序解法
// 时间复杂度：O(nlogn)
// 空间复杂度：O(n)
func smallerNumbersThanCurrent2(nums []int) []int {
    var n = len(nums)
    // 维护元素值 -> 索引关系
    var data = make([][]int, n)
    for i := 0; i < n; i++ {
        data[i] = make([]int, 2)
        data[i][0] = nums[i]
        // bug 修复：第二个值存储索引
        data[i][1] = i
    }

    // 按照元素值升序排序
    sort.Slice(data, func(i, j int) bool {
        return data[i][0] < data[j][0]
    })

    var ret = make([]int, n)
    var prev = -1
    for i := 0; i < n; i++ {
        if prev == -1 || data[i][0] != data[i - 1][0] {
            prev = i
        }
        ret[data[i][1]] = prev
    }
    return ret
}

// 方法三：计数排序
// 时间复杂度：O(n)
// 空间复杂度：O(n)
func smallerNumbersThanCurrent(nums []int) []int {
    var n = len(nums)

    var cnt = [101]int{}
    for _, num := range nums {
        cnt[num]++
    }

    for i := 1; i < 101; i++ {
        cnt[i] += cnt[i - 1]
    }

    var ret = make([]int, n)
    for i := 0; i < n; i++ {
        if nums[i] == 0 {
            ret[i] = 0
        } else {
            ret[i] = cnt[nums[i] - 1]
        }
    }
    return ret
}