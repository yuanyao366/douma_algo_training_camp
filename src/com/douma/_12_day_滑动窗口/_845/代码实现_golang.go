func longestMountain(arr []int) int {
    var n, ans, left = len(arr), 0, 0
    for left + 2 < n {
        var right = left + 1
        if arr[left] < arr[right] {
            // 找到最高点
            for right + 1 < n && arr[right] < arr[right + 1] {
                right++
            }
            if right + 1 < n && arr[right] > arr[right + 1] {
                // 找到最低点
                for right + 1 < n && arr[right] > arr[right + 1] {
                    right++
                }
                if right - left + 1 > ans {
                    ans = right - left + 1
                }
            } else {
                right++
            }
        }
        left = right
    }
    return ans
}