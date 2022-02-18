/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 思考：如果把数组变成链表的话，你会怎么实现呢？见 leetcode ：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
func sortedArrayToBST(nums []int) *TreeNode {
    var buildBST func(int, int) *TreeNode
    buildBST = func(left int, right int) *TreeNode {
        if left > right {
            return nil
        }

        var mid = left + (right - left) / 2
        var root = &TreeNode{Val:nums[mid]}

        root.Left = buildBST(left, mid - 1)
        root.Right = buildBST(mid + 1, right)

        return root
    }
    return buildBST(0, len(nums) - 1)
}