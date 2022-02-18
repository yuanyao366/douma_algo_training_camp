/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */
// 迭代解法
func postorder1(root *Node) []int {
    if root == nil {
        return []int{}
    }

    var res, stack = make([]int, 0), make([]*Node, 0)
    stack = append(stack, root)

    for len(stack) > 0 {
        var curr = stack[len(stack) - 1]
        stack = stack[:len(stack) - 1]
        res = append([]int{curr.Val}, res...)
        for _, child := range curr.Children {
            stack = append(stack, child)
        }
    }
    return res
}

// 递归解法
func postorder(root *Node) []int {
    var res = make([]int, 0)

    var dfs func(*Node)
    dfs = func(node *Node) {
        if node == nil {
            return
        }
        for _, child := range node.Children {
            dfs(child)
        }
        res = append(res, node.Val)
    }

    dfs(root)

    return res
}