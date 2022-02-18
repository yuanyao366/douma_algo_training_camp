/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type Node struct {
    node *TreeNode
    seqNo int
}

// BFS
func widthOfBinaryTree1(root *TreeNode) int {
    if root == nil {
        return 0
    }

    var maxWidth = 0
    // 用双向链表实现单向队列的功能
    var queue = list.New()
    queue.PushBack(&Node{node:root, seqNo:1}) // 入队(往链表表尾添加元素)
    for queue.Len() > 0 {
        // 每轮循环遍历处理一层的节点
        var size = queue.Len()
        var startSeqNo, endSeqNo = 0, 0
        for i := 0; i < size; i++ {
            // 出队，删除并得到链表表头元素
            var curr = queue.Remove(queue.Front()).(*Node)
            var node, seqNo = curr.node, curr.seqNo
            if i == 0 {
                startSeqNo = seqNo
            }
            if i == size - 1 {
                endSeqNo = seqNo
            }
            if node.Left != nil {
                queue.PushBack(&Node{node:node.Left, seqNo:2*seqNo})
            }
            if node.Right != nil {
                queue.PushBack(&Node{node:node.Right, seqNo:2*seqNo + 1})
            }
        }
        if endSeqNo - startSeqNo + 1 > maxWidth {
            maxWidth = endSeqNo - startSeqNo + 1
        }
    }

    return maxWidth
}


// DFS
func widthOfBinaryTree(root *TreeNode) int {
    var start, end = make([]int, 0), make([]int, 0)

    var dfs func(node *TreeNode, currLevel int, seqNo int) int
    dfs = func(node *TreeNode, currLevel int, seqNo int) int {
        if node == nil {
            return 0
        }
        if len(start) == currLevel {
            start = append(start, seqNo)
            end = append(end, seqNo)
        } else {
            end[currLevel] = seqNo
        }
        var leftMaxWidth = dfs(node.Left, currLevel + 1, 2 * seqNo)
        var rightMaxWidth = dfs(node.Right, currLevel + 1, 2 * seqNo + 1)

        var currWidth = end[currLevel] - start[currLevel] + 1
        var childMaxWidth = leftMaxWidth
        if rightMaxWidth > leftMaxWidth {
            childMaxWidth = rightMaxWidth
        }

        if currWidth > childMaxWidth {
            return currWidth
        } else {
            return childMaxWidth
        }
    }

    return dfs(root, 0, 1)
}