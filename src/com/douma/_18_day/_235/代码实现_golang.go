/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val   int
 *     Left  *TreeNode
 *     Right *TreeNode
 * }
 */

// 根据节点与父亲节点关系来实现
func lowestCommonAncestor1(root, p, q *TreeNode) *TreeNode {
    // 1. 维护子节点与其对应父节点的关系
    var parent = make(map[int]*TreeNode)
    dfs(root, parent)

    // 2. 从节点 p 开始，依次访问它的祖先
    var visited = make(map[int]bool)
    for p != nil {
        visited[p.Val] = true
        p = parent[p.Val]
    }

    // 3. 从节点 q 开始，依次访问它的祖先
    // 如果第一次遇到了 p 的祖先的话，那么这个祖先就是最近的公共祖先
    for q != nil {
        if visited[q.Val] {
            return q
        }
        q = parent[q.Val]
    }

    return nil
}

func dfs(node *TreeNode, parent map[int]*TreeNode) {
    if node == nil {
        return
    }
    if node.Left != nil {
        parent[node.Left.Val] = node
    }
    if node.Right != nil {
        parent[node.Right.Val] = node
    }

    dfs(node.Left, parent)
    dfs(node.Right, parent)
}

// DFS 后序遍历
func lowestCommonAncestor2(root, p, q *TreeNode) *TreeNode {
    if root == nil {
        return nil
    }

    if root == p || root == q {
        return root
    }

    var left = lowestCommonAncestor2(root.Left, p, q)
    var right = lowestCommonAncestor2(root.Right, p, q)

    if left == nil {
        return right
    }
    if right == nil {
        return left
    }

    return root
}


// 利用 BST 的性质
// 时间复杂度：O(logn)
// 空间复杂度：O(1)
func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
    var ancestor = root
    for ancestor != nil {
        if p.Val < ancestor.Val && q.Val < ancestor.Val {
            ancestor = ancestor.Left
        } else if p.Val > ancestor.Val && q.Val > ancestor.Val {
            ancestor = ancestor.Right
        } else {
            break
        }
    }
    return ancestor
}
