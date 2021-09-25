/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 层序遍历(BFS)
func minDepth1(root *TreeNode) int {
    if root == nil {
        return 0
    }

    var levels = 0
    // 用双向链表实现单向队列的功能
    var queue = list.New()
    queue.PushBack(root) // 入队(往链表表尾添加元素)
    for queue.Len() > 0 {
        levels++
        // 每轮循环遍历处理一层的节点
        var size = queue.Len()
        for i := 0; i < size; i++ {
            // 出队，删除并得到链表表头元素
            var curr = queue.Remove(queue.Front()).(*TreeNode)
            if curr.Left == nil && curr.Right == nil {
                return levels
            }
            if curr.Left != nil {
                queue.PushBack(curr.Left)
            }
            if curr.Right != nil {
                queue.PushBack(curr.Right)
            }
        }
    }

    return levels
}

type Node struct {
    node *TreeNode
    depth int
}

// DFS 迭代 - 前序遍历
func minDepth2(root *TreeNode) int {
    if root == nil {
        return 0
    }

    // bug 修复：这里需要初始化为最大值
    var res, stack = math.MaxInt32, make([]*Node, 0)
    stack = append(stack, &Node{node:root, depth:1})
    for len(stack) > 0 {
        var node = stack[len(stack) - 1]
        stack = stack[:len(stack) - 1]

        var curr, currDepth = node.node, node.depth
        if curr.Left == nil && curr.Right == nil {
            if currDepth < res {
                res = currDepth
            }
        }

        if curr.Right != nil {
            stack = append(stack, &Node{node:curr.Right, depth:currDepth + 1})
        }
        if curr.Left != nil {
            stack = append(stack, &Node{node:curr.Left, depth:currDepth + 1})
        }
    }

    return res
}


// DFS 递归 - 前序遍历
func minDepth3(root *TreeNode) int {
    // bug 修复：需要加上特判，要不然会返回最大值
    if root == nil {
        return 0
    }
    var res = math.MaxInt32

    var preOrder func(node *TreeNode, currDepth int)
    preOrder = func(node *TreeNode, currDepth int) {
        if node == nil {
            return
        }

        if node.Left == nil && node.Right == nil {
            if currDepth < res {
                res = currDepth
            }
        }

        preOrder(node.Left, currDepth + 1)
        preOrder(node.Right, currDepth + 1)
    }

    preOrder(root, 1)

    return res
}


// DFS 递归 - 后序遍历
// 计算以 root 为根节点的二叉树的最小深度
func minDepth(root *TreeNode) int {
    if root == nil {
        return 0
    }

    var leftMinDepth = minDepth(root.Left)
    var rightMinDepth = minDepth(root.Right)

    if root.Left == nil {
        return rightMinDepth + 1
    } else if root.Right == nil {
        return leftMinDepth + 1
    } else {
        if leftMinDepth < rightMinDepth {
            return leftMinDepth + 1
        } else {
            return rightMinDepth + 1
        }
    }
}