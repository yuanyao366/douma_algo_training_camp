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
 * @return {number}
 */
var diameterOfBinaryTree = function(root) {
    if (!root) return 0

    let res = 0
    const maxDepth = (node) => {
        if (!node) return 0
        const leftMaxDepth = maxDepth(node.left)
        const rightMaxDepth = maxDepth(node.right)
        res = Math.max(res, leftMaxDepth + rightMaxDepth)
        return Math.max(leftMaxDepth, rightMaxDepth) + 1
    }

    maxDepth(root)
    return res
};