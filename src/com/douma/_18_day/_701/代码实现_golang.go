/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 1. 迭代
// O(logn)
func insertIntoBST1(root *TreeNode, val int) *TreeNode {
    if root == nil {
        return &TreeNode{Val:val}
    }
    var curr = root
    for curr != nil {
        if val < curr.Val && curr.Left == nil {
            curr.Left = &TreeNode{Val:val}
            return root
        } else if val > curr.Val && curr.Right == nil {
            curr.Right = &TreeNode{Val:val}
            return root
        }

        if val < curr.Val {
            curr = curr.Left
        } else {
            curr = curr.Right
        }
    }

    return root
}

// 递归
// 将 val 插入到以 root 为根节点的二叉搜索树中，并返回插入节点后的二叉搜索树的根节点
func insertIntoBST(root *TreeNode, val int) *TreeNode {
    if root == nil {
        return &TreeNode{Val:val}
    }
    if val < root.Val {
        root.Left = insertIntoBST(root.Left, val)
    } else {
        root.Right = insertIntoBST(root.Right, val)
    }
    return root
}