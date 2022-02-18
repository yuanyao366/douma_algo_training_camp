func validMountainArray(arr []int) bool {
    n, i := len(arr), 0

    // 1. 找到最高点
    // bug 修复：i = n - 1 的话，数组会越界
    for i < n - 1 && arr[i] < arr[i + 1] {
        i++
    }

    // 2. 判断：最高点不能是第一个和最后一个元素
    if i == 0 || i == n - 1 {
        return false
    }

    // 3. 从最高点往后递减扫描
    for i < n - 1 && arr[i] > arr[i + 1] {
        i++
    }
    // 4. 如果 i 指向数组最后一个元素，则返回 true，否则返回 false
    return i == n - 1
}