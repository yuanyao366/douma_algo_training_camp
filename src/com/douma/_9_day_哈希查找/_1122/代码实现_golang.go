
// 自定义排序
func relativeSortArray1(arr1 []int, arr2 []int) []int {
    var m = make(map[int]int)
    for i := range arr2 {
        m[arr2[i]] = i
    }

    sort.Slice(arr1, func(i, j int)bool {
        x, okx := m[arr1[i]]
        y, oky := m[arr1[j]]
        if okx {
            if oky {
                return x < y
            } else {
                return true
            }
        } else {
            if oky {
                return false
            } else {
                return arr1[i] < arr1[j]
            }
        }
    })
    return arr1
}

// 计数排序
func relativeSortArray(arr1 []int, arr2 []int) []int {
    var count = make([]int, 1001)
    for _, num := range arr1 {
        count[num]++
    }

    var index = 0
    for _, num := range arr2 {
        for i := 0; i < count[num]; i++ {
            arr1[index] = num
            index++
        }
        // 清 0 的原因：
        // 在 arr1 中等于 num 的所有的元素都放在一起了
        // 也就是 num 排好序了，为了可以区分出已经排好序，和还没排序的元素
        // 我们将排好序的元素出现的次数清 0
        count[num] = 0
        // 清 0 后，在下面的循环中就不用处理了，下面的循环只要处理在 arr2 中没出现的元素了
    }

    for num := 0; num < 1001; num++ {
        for i := 0; i < count[num]; i++ {
            arr1[index] = num
            index++
        }
    }

    return arr1
}