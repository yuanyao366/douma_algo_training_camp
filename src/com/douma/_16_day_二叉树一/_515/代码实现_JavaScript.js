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
 * @return {number[]}
 */
// BFS
var largestValues1 = function(root) {
    if (!root) return []

    const res = [], queue = []
    queue.push(root)
    while (queue.length) {
        const size = queue.length
        let maxValue = -Math.pow(2, 31)
        for (let i = 0; i < size; i++) {
            const curr = queue.shift();
            maxValue = Math.max(maxValue, curr.val)
            if (curr.left) queue.push(curr.left)
            if (curr.right) queue.push(curr.right)
        }
        res.push(maxValue)
    }

    return res
};

// DFS
var largestValues = function(root) {
    if (!root) return []

    const preorder = (node, currLevel, res) => {
        if (!node) return
        if (res.length == currLevel) {
            res.push(node.val)
        } else {
            const maxValue = Math.max(res[currLevel], node.val)
            res[currLevel] = maxValue
        }
        preorder(node.left, currLevel + 1, res)
        preorder(node.right, currLevel + 1, res)
    }

    const res = []
    preorder(root, 0, res)
    return res
}