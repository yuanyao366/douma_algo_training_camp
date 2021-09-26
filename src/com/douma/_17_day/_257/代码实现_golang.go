/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

// DFS
func binaryTreePaths1(root *TreeNode) []string {
    var res = make([]string, 0)

    var dfs func(*TreeNode, string)
    dfs = func(node *TreeNode, parentPath string) {
        if node == nil {
            return
        }
        if node.Left == nil && node.Right == nil {
            res = append(res, parentPath + strconv.Itoa(node.Val))
            return
        }
        dfs(node.Left, parentPath + strconv.Itoa(node.Val) + "->")
        dfs(node.Right, parentPath + strconv.Itoa(node.Val) + "->")
    }

    dfs(root, "")
    return res
}

// BFS
func binaryTreePaths(root *TreeNode) []string {
    if root == nil {
        return []string{}
    }

    var res = make([]string, 0)
    // 用双向链表实现单向队列的功能
    var nodeQueue = list.New()
    nodeQueue.PushBack(root) // 入队(往链表表尾添加元素)

    var pathQueue = list.New()
    pathQueue.PushBack(strconv.Itoa(root.Val))
    for nodeQueue.Len() > 0 {
        // 出队，删除并得到链表表头元素
        var node = nodeQueue.Remove(nodeQueue.Front()).(*TreeNode)
        var path = pathQueue.Remove(pathQueue.Front()).(string)

        if node.Left == nil && node.Right == nil {
            res = append(res, path)
            continue
        }

        if node.Left != nil {
            nodeQueue.PushBack(node.Left)
            pathQueue.PushBack(path + "->" + strconv.Itoa(node.Left.Val))
        }
        if node.Right != nil {
            nodeQueue.PushBack(node.Right)
            pathQueue.PushBack(path + "->" + strconv.Itoa(node.Right.Val))
        }
    }

    return res
}