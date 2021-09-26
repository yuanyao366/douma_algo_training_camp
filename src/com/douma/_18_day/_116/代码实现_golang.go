/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Next *Node
 * }
 */
 // 1. BFS 层序遍历
func connect1(root *Node) *Node {
	if root == nil {
        return nil
    }

    // 用双向链表实现单向队列的功能
    var queue = list.New()
    queue.PushBack(root) // 入队(往链表表尾添加元素)
    for queue.Len() > 0 {
        // 每轮循环遍历处理一层的节点
        var size = queue.Len()
        for i := 0; i < size; i++ {
            // 出队，删除并得到链表表头元素
            var curr = queue.Remove(queue.Front()).(*Node)
            // 如果当前节点不是这一层的最后一个节点，则设置 next
            if i != size - 1 {
                curr.Next = queue.Front().Value.(*Node)
            }
            if curr.Left != nil {
                queue.PushBack(curr.Left)
            }
            if curr.Right != nil {
                queue.PushBack(curr.Right)
            }
        }
    }

    return root
}


// 2. 双指针
func connect2(root *Node) *Node {
	if root == nil {
        return nil
    }

    var left, curr *Node = root, nil
    for left.Left != nil {
        curr = left
        for curr != nil {
            curr.Left.Next = curr.Right
            if curr.Next != nil {
                curr.Right.Next = curr.Next.Left
            }
            curr = curr.Next
        }
        left = left.Left
    }
    return root
}

// 3. DFS
func connect(root *Node) *Node {
    var dfs func(*Node)
    dfs = func(node *Node) {
        if node == nil {
            return
        }

        var left, right = node.Left, node.Right
        for left != nil {
            left.Next = right
            left = left.Right
            right = right.Left
        }

        dfs(node.Left)
        dfs(node.Right)
    }

    dfs(root)
    return root
}
