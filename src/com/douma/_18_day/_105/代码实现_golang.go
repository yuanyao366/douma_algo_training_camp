/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 迭代
func buildTree1(preorder []int, inorder []int) *TreeNode {
    var root = &TreeNode{Val:preorder[0]}
    var stack, inorderIndex = make([]*TreeNode, 0), 0
    stack = append(stack, root)
    for i := 1; i < len(preorder); i++ {
        var childVal = preorder[i]
        var parentNode = stack[len(stack) - 1]
        if parentNode.Val != inorder[inorderIndex] {
            parentNode.Left = &TreeNode{Val:childVal}
            stack = append(stack, parentNode.Left)
        } else {
            // 找到离右子节点最近的父亲节点
            for len(stack) > 0 && inorder[inorderIndex] == stack[len(stack) - 1].Val {
                parentNode = stack[len(stack) - 1]
                stack = stack[:len(stack) - 1]
                inorderIndex++
            }
            parentNode.Right = &TreeNode{Val:childVal}
            stack = append(stack, parentNode.Right)
        }
    }
    return root
}


// 递归
func buildTree(preorder []int, inorder []int) *TreeNode {
    var rootIndex, idxMap = 0, make(map[int]int)
    for i := range inorder {
        idxMap[inorder[i]] = i
    }

    var dfs func(int, int) *TreeNode
    dfs = func(left int, right int) *TreeNode {
        if left > right {
            return nil
        }
        var rootVal = preorder[rootIndex]
        rootIndex++
        var root = &TreeNode{Val:rootVal}

        var mid = idxMap[rootVal]

        root.Left = dfs(left, mid - 1)
        root.Right = dfs(mid + 1, right)

        return root
    }

    return dfs(0, len(inorder) - 1)
}