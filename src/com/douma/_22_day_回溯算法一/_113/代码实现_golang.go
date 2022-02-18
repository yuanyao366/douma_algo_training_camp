/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, targetSum int) [][]int {
    var res, path = make([][]int, 0), make([]int, 0)

    var dfs func(*TreeNode, int)
    dfs = func(node *TreeNode, target int) {
        if node == nil {
            return
        }
        path = append(path, node.Val)
        target -= node.Val
        if node.Left == nil && node.Right == nil && target == 0 {
            res = append(res, append([]int(nil), path...))
        }

        dfs(node.Left, target)
        dfs(node.Right, target)
        path = path[:len(path) - 1]
    }

    dfs(root, targetSum)

    return res
}