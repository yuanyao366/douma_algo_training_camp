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
 * @return {string[]}
 */
var binaryTreePaths = function(root) {
    res = []
    const dfs = (node, path) => {
        if (!node) return
        if (!node.left && !node.right) {
            res.push(path + node.val.toString())
            return
        }
        dfs(node.left, path + node.val.toString() + "->")
        dfs(node.right, path + node.val.toString() + "->")
    }
    dfs(root, "")
    return res
};