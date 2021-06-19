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
 * @return {void} Do not return anything, modify root in-place instead.
 */
var recoverTree = function(root) {
    let x = null, y = null, prev = null

    const dfs = (node) => {
        if (!node) return
        dfs(node.left)
        if (prev && node.val < prev.val) {
            y = node
            if (!x) {
                x = prev
            } else {
                return
            }
        }
        prev = node;
        dfs(node.right)
    }

    dfs(root)
    const tmp = x.val
    x.val = y.val
    y.val = tmp
};