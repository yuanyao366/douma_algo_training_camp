/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxPathSum(root *TreeNode) int {
    // bug 修复：因为是求最大值，所以初始化为最小值
    var maxSum = math.MinInt32

    // 返回以 node 为根节点的子树的最大贡献值
    var maxGain func(*TreeNode) int
    maxGain = func(node *TreeNode) int {
        if node == nil {
            return 0
        }

        // 递归计算左右子节点的最大贡献值
        var leftGain = maxGain(node.Left)
        if leftGain < 0 {
            leftGain = 0
        }
        var rightGain = maxGain(node.Right)
        if rightGain < 0 {
            rightGain = 0
        }

        if leftGain + rightGain + node.Val > maxSum {
            maxSum = leftGain + rightGain + node.Val
        }

        if leftGain > rightGain {
            return leftGain + node.Val
        } else {
            return rightGain + node.Val
        }
    }

    maxGain(root)
    return maxSum
}