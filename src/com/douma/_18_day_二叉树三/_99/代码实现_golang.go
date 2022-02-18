/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func recoverTree(root *TreeNode)  {
    var prev, x, y *TreeNode = nil, nil, nil

    var dfs func(*TreeNode)
    dfs = func(node *TreeNode) {
        if node == nil {
            return
        }

        dfs(node.Left)
        // 处理当前的节点
        if prev != nil && node.Val < prev.Val {
            y = node
            if x == nil {
                x = prev
            } else {
                return
            }
        }
        prev = node
        dfs(node.Right)
    }

    dfs(root)

    x.Val, y.Val = y.Val, x.Val
}