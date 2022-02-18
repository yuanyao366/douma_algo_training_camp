/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 1. 中序遍历(先拿到遍历结果，再验证顺序性)
func isValidBST1(root *TreeNode) bool {
    var res = make([]int, 0)

    var inOrder func(*TreeNode)
    inOrder = func(node *TreeNode) {
        if node == nil {
            return
        }
        inOrder(node.Left)
        res = append(res, node.Val)
        inOrder(node.Right)
    }

    inOrder(root)

    for i := 1; i < len(res); i++ {
        if res[i] <= res[i - 1] {
            return false
        }
    }
    return true
}

// 2. 中序遍历 (边遍历边验证顺序性)
func isValidBST2(root *TreeNode) bool {
    var prev *TreeNode = nil
    var isBST = true

    var inOrder func(*TreeNode)
    inOrder = func(node *TreeNode) {
        if node == nil {
            return
        }
        inOrder(node.Left)
        if prev != nil && node.Val <= prev.Val {
            isBST = false
            return
        }
        prev = node
        inOrder(node.Right)
    }

    inOrder(root)

    return isBST
}

// 3. 后序遍历
func isValidBST(root *TreeNode) bool {

    var dfs func(*TreeNode, int64, int64) bool
    dfs = func(node *TreeNode, lower int64, upper int64) bool {
        if node == nil {
            return true
        }
        if int64(node.Val) <= lower || int64(node.Val) >= upper {
            return false
        }

        var isLeftBST = dfs(node.Left, lower, int64(node.Val))
        var isRightBST = dfs(node.Right, int64(node.Val), upper)

        return isLeftBST && isRightBST
    }

    return dfs(root, math.MinInt64, math.MaxInt64)
}