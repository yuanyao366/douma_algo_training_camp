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
 * @param {number} targetSum
 * @return {number[][]}
 */
var pathSum = function(root, targetSum) {
    const res = [], path = []

    const dfs = (node, targetSum) => {
        if (!node) return
        path.push(node.val)
        targetSum -= node.val
        if (!node.left && !node.right && targetSum == 0) {
            res.push(path.slice())
        }
        dfs(node.left, targetSum)
        dfs(node.right, targetSum)
        path.pop()
    }

    dfs(root, targetSum)
    return res
};