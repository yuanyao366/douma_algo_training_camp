/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
// DFS
func countNodes1(root *TreeNode) int {
    if root == nil {
        return 0
    }

    var left = countNodes1(root.Left)
    var right = countNodes1(root.Right)

    return left + right + 1
}

// 二分查找
// 时间复杂度：O((logn)^2)
func countNodes(root *TreeNode) int {
    if root == nil {
        return 0
    }

    // 计算完全二叉树的最大层数
    var level, node = 0, root
    for node.Left != nil {
        level++
        node = node.Left
    }

    // 完全二叉树的节点数的范围是：[2^level, 2^(level + 1) - 1]
    var low, high = 1 << level, (1 << (level + 1)) - 1
    for low < high {
        // 这里需要 + 1
        var mid = low + (high - low + 1) / 2
        if exists(root, level, mid) {
            low = mid
        } else {
            high = mid - 1
        }
    }

    return low
}

func exists(root *TreeNode, level int, mid int) bool {
    var node, mask = root, 1 << (level - 1)
    for node != nil && mask > 0 {
        if (mask & mid) == 0 {
            node = node.Left
        } else {
            node = node.Right
        }
        mask >>= 1
    }
    return node != nil
}

