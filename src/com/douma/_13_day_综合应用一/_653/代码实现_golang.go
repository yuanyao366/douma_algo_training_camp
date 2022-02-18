/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
 // 方法一：中序遍历 + 二分查找
func findTarget1(root *TreeNode, k int) bool {
    var nums = make([]int, 0)

    var inOrder func(node *TreeNode)
    inOrder = func(node *TreeNode) {
        if node == nil {
            return
        }
        inOrder(node.Left)
        nums = append(nums, node.Val)
        inOrder(node.Right)
    }

    inOrder(root)

    var left, right = 0, len(nums) - 1
    for left < right {
        var sum = nums[left] + nums[right]
        if sum == k {
            return true
        } else if sum < k {
            left++
        } else {
            right--
        }
    }
    return false
}


 // 方法二：哈希查找
func findTarget(root *TreeNode, k int) bool {
    var set = make(map[int]bool)

    var find func(node *TreeNode) bool
    find = func(node *TreeNode) bool {
        if node == nil {
            return false
        }
        if set[k - node.Val] {
            return true
        }
        set[node.Val] = true
        return find(node.Left) || find(node.Right)
    }

    return find(root)
}