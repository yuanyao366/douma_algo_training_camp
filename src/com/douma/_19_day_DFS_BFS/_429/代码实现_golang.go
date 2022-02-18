/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

// BFS
func levelOrder1(root *Node) [][]int {
    if root == nil {
        return [][]int{}
    }

    var res = make([][]int, 0)
    // 用双向链表实现单向队列的功能
    var queue = list.New()
    queue.PushBack(root) // 入队(往链表表尾添加元素)
    for queue.Len() > 0 {
        // 每轮循环遍历处理一层的节点
        var size, levelNodes = queue.Len(), make([]int, 0)
        for i := 0; i < size; i++ {
            // 出队，删除并得到链表表头元素
            var curr = queue.Remove(queue.Front()).(*Node)
            levelNodes = append(levelNodes, curr.Val)
            for _, child := range curr.Children {
                queue.PushBack(child)
            }
        }
        res = append(res, levelNodes)
    }

    return res
}

// DFS
func levelOrder(root *Node) [][]int {
    var res = make([][]int, 0)

    var dfs func(*Node, int)
    dfs = func(node *Node, currLevel int) {
        if node == nil {
            return
        }
        if len(res) == currLevel {
            var levelNodes = make([]int, 0)
            levelNodes = append(levelNodes, node.Val)
            res = append(res, levelNodes)
        } else {
            res[currLevel] = append(res[currLevel], node.Val)
        }
        for _, child := range node.Children {
            dfs(child, currLevel + 1)
        }
    }

    dfs(root, 0)

    return res
}