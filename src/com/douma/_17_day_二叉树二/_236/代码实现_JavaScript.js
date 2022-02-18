/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {TreeNode}
 */

var lowestCommonAncestor1 = function(root, p, q) {
    if (!root) return null

    const parents = new Map()
    const visited = new Set()
    const dfs = (node) => {
        if (!node) return null
        if (node.left) parents.set(node.left.val, node)
        if (node.right) parents.set(node.right.val, node)
        dfs(node.left)
        dfs(node.right)
    }

    dfs(root)

    while (p) {
        visited.add(p)
        p = parents.get(p.val)
    }

    while (q) {
        if (visited.has(q)) return q
        q = parents.get(q.val)
    }

    return null
};

// DFS 后序遍历
var lowestCommonAncestor = function(root, p, q) {
    if (!root) return null
    if (root == p || root == q) return root

    const left = lowestCommonAncestor(root.left, p, q)
    const right = lowestCommonAncestor(root.right, p, q)

    if (left == null) return right
    if (right == null) return left
    return root
}