/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */
// 迭代
func preorder1(root *Node) []int {
    if root == nil {
        return []int{}
    }

    var res, stack = make([]int, 0), make([]*Node, 0)
    stack = append(stack, root)

    for len(stack) > 0 {
        var curr = stack[len(stack) - 1]
        stack = stack[:len(stack) - 1]
        res = append(res, curr.Val)

        // 将 N 个子节点放入到栈中
        for i := len(curr.Children) - 1; i >= 0; i-- {
            stack = append(stack, curr.Children[i])
        }
    }

    return res
}

// 递归
func preorder(root *Node) []int {
    var res = make([]int, 0)

    var dfs func(*Node)
    dfs = func(node *Node) {
        if node == nil {
            return
        }
        res = append(res, node.Val)
        for _, child := range node.Children {
            dfs(child)
        }
    }

    dfs(root)

    return res
}