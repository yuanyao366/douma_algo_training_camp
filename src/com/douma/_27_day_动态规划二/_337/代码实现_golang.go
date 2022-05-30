/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rob(root *TreeNode) int {

    var dfs func(*TreeNode) [2]int
    dfs = func(node *TreeNode) [2]int {
        if node == nil {
            return [2]int{}
        }

        var left = dfs(node.Left)
        var right = dfs(node.Right)

        var res = [2]int{}
        // 1. 选择不偷当前的节点，当前节点能偷到的最大钱数
        //          = 左孩子能偷到的最大钱 + 右孩子能偷到的最大钱
        /*
        选择不偷当前的节点的话，那么有四种情况：
            ① 偷了左子节点，偷了右子节点  --》 left[1] + right[1]
            ② 偷了左子节点，不偷右子节点  --》 left[1] + right[0]
            ③ 不偷左子节点，偷了右子节点  --》 left[0] + right[1]
            ④ 不偷左子节点，不偷右子节点  --》 left[0] + right[0]
         */
        res[0] = max(left[0], left[1]) + max(right[0], right[1])
        // 2. 选择偷当前的节点：当前节点能偷到的最大钱数
        //              = 左孩子选择自己不偷时能得到的最大钱 +
        //                  右孩子选择不偷时能得到的最大钱 + 当前节点的钱数
        res[1] = left[0] + right[0] + node.Val
        return res
    }

    var res = dfs(root)
    return max(res[0], res[1])
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}