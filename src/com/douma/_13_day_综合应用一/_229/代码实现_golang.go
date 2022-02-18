 // 哈希查找
func majorityElement1(nums []int) []int {
    var cntMap = make(map[int]int)
    for _, num := range nums {
        cntMap[num]++
    }

    var res = make([]int, 0)
    for num, cnt := range cntMap {
        if cnt > len(nums) / 3 {
            res = append(res, num)
        }
    }
    return res
}

// Boyer-Moore 投票算法
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func majorityElement(nums []int) []int {
    var candidate1, candidate2 = 0, 0
    var count1, count2 = 0, 0
    for _, num := range nums {
        if num == candidate1 {
            count1++
        } else if num == candidate2 {
            count2++
        } else if count1 == 0 {
            candidate1 = num
            count1++
        } else if count2 == 0 {
            candidate2 = num
            count2++
        } else {
            count1--
            count2--
        }
    }

    count1, count2 = 0, 0
    for _, num := range nums {
        if num == candidate1 {
            count1++
        } else if num == candidate2 {
            count2++
        }
    }

    var res = make([]int, 0)
    if count1 > len(nums) / 3 {
        res = append(res, candidate1)
    }
    if count2 > len(nums) / 3 {
        res = append(res, candidate2)
    }
    return res
}