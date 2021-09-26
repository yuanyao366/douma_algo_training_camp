/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 先前序遍历，得到结果，再串联成链表
func flatten1(root *TreeNode)  {
    // 前序遍历
    var res = make([]*TreeNode, 0)

    var preOrder func(node *TreeNode)
    preOrder = func(node *TreeNode) {
        if node == nil {
            return
        }
        res = append(res, node)
        preOrder(node.Left)
        preOrder(node.Right)
    }

    preOrder(root)

    // 串联成链表
    for i := 1; i < len(res); i++ {
        var prev, curr = res[i - 1], res[i]
        prev.Left = nil
        prev.Right = curr
    }
}

// 边遍历边串联
func flatten2(root *TreeNode)  {
    if root == nil {
        return
    }

    var stack = make([]*TreeNode, 0)
    stack = append(stack, root)
    var prev *TreeNode = nil
    for len(stack) > 0 {
        var curr = stack[len(stack) - 1]
        stack = stack[:len(stack) - 1]
        if prev != nil {
            prev.Left = nil
            prev.Right = curr
        }

        if curr.Right != nil {
            stack = append(stack, curr.Right)
        }
        if curr.Left != nil {
            stack = append(stack, curr.Left)
        }

        prev = curr
    }
}


// 原地改变指针
func flatten(root *TreeNode)  {
    if root == nil {
        return
    }
    var curr = root
    for curr != nil {
        if curr.Left != nil {
            var left, prev = curr.Left, curr.Left
            for prev.Right != nil {
                prev = prev.Right
            }
            prev.Right = curr.Right
            curr.Left = nil
            curr.Right = left
        }
        curr = curr.Right
    }
}




