/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 翻转以 root 为根的二叉树，然后返回翻转后的二叉树的根节点
func invertTree(root *TreeNode) *TreeNode {
    if root == nil {
        return nil
    }

    if root.Left == nil && root.Right == nil {
        return root
    }

    var left = invertTree(root.Left)
    var right = invertTree(root.Right)

    root.Left = right
    root.Right = left

    return root
}