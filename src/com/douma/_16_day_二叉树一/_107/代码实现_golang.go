/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// 在 102 号算法题的基础上，对结果反转即可
func levelOrderBottom1(root *TreeNode) [][]int {
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
            var curr = queue.Remove(queue.Front()).(*TreeNode)
            levelNodes = append(levelNodes, curr.Val)
            if curr.Left != nil {
                queue.PushBack(curr.Left)
            }
            if curr.Right != nil {
                queue.PushBack(curr.Right)
            }
        }
        res = append(res, levelNodes)
    }
    reverse(res)
    return res
}

func reverse(arr [][]int) {
    var left, right = 0, len(arr) - 1
    for left < right {
        arr[left], arr[right] = arr[right], arr[left]
        left++
        right--
    }
}


func levelOrderBottom(root *TreeNode) [][]int {
    var res = make([][]int, 0)

    var preOrder func(*TreeNode, int)
    preOrder = func(node *TreeNode, currLevel int) {
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
        preOrder(node.Left, currLevel + 1)
        preOrder(node.Right, currLevel + 1)
    }

    preOrder(root, 0)
    reverse(res)
    return res
}