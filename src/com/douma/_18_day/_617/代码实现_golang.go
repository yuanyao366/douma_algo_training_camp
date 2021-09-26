/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 合并以 root1、root2 为根节点的两棵树，返回合并之后的新的二叉树的根节点
func mergeTrees(root1 *TreeNode, root2 *TreeNode) *TreeNode {
    if root1 == nil {
        return root2
    }
    if root2 == nil {
        return root1
    }

    var newRoot = &TreeNode{Val:root1.Val + root2.Val}

    newRoot.Left = mergeTrees(root1.Left, root2.Left)
    newRoot.Right = mergeTrees(root1.Right, root2.Right)

    return newRoot
}