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
var maxPathSum = function(root) {
    let maxSum = -Math.pow(2, 31)

    const maxGain = (node) => {
        if (!node) return 0

        const leftGain = Math.max(maxGain(node.left), 0)
        const rightGain = Math.max(maxGain(node.right), 0)

        maxSum = Math.max(maxSum, leftGain + rightGain + node.val)
        return Math.max(leftGain, rightGain) + node.val
    }

    maxGain(root)
    return maxSum
};