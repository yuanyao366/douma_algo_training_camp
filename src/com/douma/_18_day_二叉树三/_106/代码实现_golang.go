/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(inorder []int, postorder []int) *TreeNode {
    var rootIndex, idxMap = len(postorder) - 1, make(map[int]int)
    for i := range inorder {
        idxMap[inorder[i]] = i
    }

    var dfs func(int, int) *TreeNode
    dfs = func(left int, right int) *TreeNode {
        if left > right {
            return nil
        }
        var rootVal = postorder[rootIndex]
        rootIndex--
        var root = &TreeNode{Val:rootVal}

        var mid = idxMap[rootVal]

        root.Right = dfs(mid + 1, right)
        root.Left = dfs(left, mid - 1)

        return root
    }

    return dfs(0, len(inorder) - 1)
}