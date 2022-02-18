/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findMode(root *TreeNode) []int {
    var ans = make([]int, 0)
    var prevNum, count, maxCount = 0, 0, 0

    var update func(int)
    update = func(val int) {
        if val == prevNum {
            count++
        } else {
            prevNum = val
            count = 1
        }

        if count == maxCount {
            ans = append(ans, val)
        } else if count > maxCount {
            ans = make([]int, 0)
            ans = append(ans, val)
            maxCount = count
        }
    }

    var dfs func(*TreeNode)
    dfs = func(node *TreeNode) {
        if node == nil {
            return
        }
        dfs(node.Left)
        update(node.Val)
        dfs(node.Right)
    }

    dfs(root)

    return ans
}