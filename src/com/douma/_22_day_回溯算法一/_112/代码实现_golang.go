/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func hasPathSum(root *TreeNode, targetSum int) bool {
    if root == nil {
        return false
    }

    if root.Left == nil && root.Right == nil {
        return targetSum - root.Val == 0
    }

    var leftHasPathSum = hasPathSum(root.Left, targetSum - root.Val)
    if leftHasPathSum {
        return true
    }

    var rightHasPathSum = hasPathSum(root.Right, targetSum - root.Val)
    return leftHasPathSum || rightHasPathSum
}