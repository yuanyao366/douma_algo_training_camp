/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func diameterOfBinaryTree(root *TreeNode) int {
    var ans = 0

    var maxDepth func(*TreeNode) int
    maxDepth = func(node *TreeNode) int {
        if node == nil {
            return 0
        }

        var leftMaxDepth = maxDepth(node.Left)
        var rightMaxDepth = maxDepth(node.Right)

        if leftMaxDepth + rightMaxDepth > ans {
            ans = leftMaxDepth + rightMaxDepth
        }

        if (leftMaxDepth > rightMaxDepth) {
            return leftMaxDepth + 1
        } else {
            return rightMaxDepth + 1
        }
    }

    maxDepth(root)

    return ans
}