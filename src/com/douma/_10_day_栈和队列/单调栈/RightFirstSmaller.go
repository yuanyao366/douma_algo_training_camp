/*
    题目：找出数组中右边第一个比我小的元素
    一个整数数组 nums，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。
    输入：[5, 2]
    输出：[1, -1]

    解释：
        因为元素 5 的右边离我最近且比我小的位置应该是 nums[1]，
        最后一个元素 2 右边没有比 2 小的元素，所以应该输出 -1。
 */

func findRightSmall(nums []int) []int {
    var ans, stack = make([]int, len(nums), []int{}

    for i := range nums {
        var x = nums[i]
        // 单调递增栈
        for len(stack) > 0 && x < nums[stack[len(stack) - 1]] {
            ans[stack[len(stack) - 1]] = i
            stack = stack[:len(stack) - 1]
        }
        stack = append(stack, i) // 索引
    }

    for len(stack) > 0 {
        ans[stack[len(stack) - 1]] = -1
        stack = stack[:len(stack) - 1]
    }

    return ans
}