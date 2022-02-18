
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
        count[num] = 0;
    }

    for num := 0; num < 1001; num++ {
        for i := 0; i < count[num]; i++ {
            arr1[index] = num
            index++
        }
    }

    return arr1
}