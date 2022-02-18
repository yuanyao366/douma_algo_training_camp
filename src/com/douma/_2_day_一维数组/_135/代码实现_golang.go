
// 直接模拟，暴力解法 O(n^2)
func candy1(ratings []int) int {
    n := len(ratings)
    candies := make([]int, n)
    for i := 0; i < n; i++ {
        candies[i] = 1
    }
    hasChanged := true

    for hasChanged {
        hasChanged = false
        for i := 0; i < n; i++ {
            if i != n - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1] {
                candies[i] = candies[i + 1] + 1
                hasChanged = true
            }
            if i != 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1] {
                candies[i] = candies[i - 1] + 1
                hasChanged = true
            }
        }
    }
    sum := 0
    for _, candy := range candies {
        sum += candy
    }
    return sum
}



// 两个数组 + 两次遍历 O(n)
func candy2(ratings []int) int {
    n := len(ratings)
    left2right := make([]int, n)
    for i := 0; i < n; i++ {
        left2right[i] = 1
    }
    right2left := make([]int, n)
    for i := 0; i < n; i++ {
        right2left[i] = 1
    }

    for i := 0; i < n; i++ {
        if i != 0 && ratings[i] > ratings[i - 1] {
            left2right[i] = left2right[i - 1] + 1
        }
    }

    sum := 0
    for i := n - 1; i >= 0; i-- {
        if i != n - 1 && ratings[i] > ratings[i + 1] {
            right2left[i] = right2left[i + 1] + 1
        }

        if left2right[i] > right2left[i] {
            sum += left2right[i]
        } else {
            sum += right2left[i]
        }
    }

    return sum
}

// 一个数组 + 两次遍历 O(n)
func candy(ratings []int) int {
    n := len(ratings)
    left2right := make([]int, n)
    for i := 0; i < n; i++ {
        left2right[i] = 1
    }

    for i := 0; i < n; i++ {
        if i != 0 && ratings[i] > ratings[i - 1] {
            left2right[i] = left2right[i - 1] + 1
        }
    }

    sum, right := 0, 0
    for i := n - 1; i >= 0; i-- {
        if i != n - 1 && ratings[i] > ratings[i + 1] {
            right++
        } else {
            right = 1
        }

        if left2right[i] > right {
            sum += left2right[i]
        } else {
            sum += right
        }
    }

    return sum
}