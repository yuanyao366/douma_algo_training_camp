// 暴力解法
// 时间复杂度：O(m*n)
// 空间复杂度：O(1)
func numJewelsInStones1(jewels string, stones string) int {
    var ans = 0
    for _, c := range stones {
        for _, j := range jewels {
            if c == j {
                ans++
            }
        }
    }
    return ans
}

// 哈希查找
// 时间复杂度：O(n)
// 空间复杂度：O(m)
func numJewelsInStones(jewels string, stones string) int {
    var jewelsMap = make(map[rune]bool)
    for _, c := range jewels {
        jewelsMap[c] = true
    }
    var ans = 0
    for _, c := range stones {
        if jewelsMap[c] {
            ans++
        }
    }
    return ans
}


// 哈希查找 - 数组代替 map
// 时间复杂度：O(n)
// 空间复杂度：O(1)
func numJewelsInStones3(jewels string, stones string) int {
    var len = 'z' - 'A' + 1
    var jewelsMap = make([]int, len)
    for _, c := range jewels {
        jewelsMap[c - 'A'] = 1
    }
    var ans = 0
    for _, c := range stones {
        if jewelsMap[c - 'A'] == 1 {
            ans++
        }
    }
    return ans
}