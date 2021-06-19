/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
// 1. 中序遍历 (边遍历边验证顺序性)
var isValidBST1 = function(root) {
    let isBST = true, prev = null

    const dfs = (node) => {
        if (!node) return
        dfs(node.left)
        if (prev && node.val <= prev.val) {
            isBST = false
        }
        prev = node
        dfs(node.right)
    }

    dfs(root)
    return isBST
};

// 2. 后序遍历
var isValidBST = function(root) {

    const isValidBST = (node, lower, upper) => {
        if (!node) return true
        if (node.val <= lower || node.val >= upper) {
            return false
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper)
    }

    return isValidBST(root, -Infinity, Infinity)
};