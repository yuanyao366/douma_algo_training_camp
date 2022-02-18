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
// DFS 前序遍历
var sumOfLeftLeaves1 = function(root) {
    if (!root) return 0

    let sum = 0
    const preorder = (node, parent) => {
        if (!node) return
        if (!node.left && !node.right && parent.left == node) {
            sum += node.val
        }
        preorder(node.left, node)
        preorder(node.right, node)
    }

    preorder(root, root)
    return sum
};

// DFS 后序遍历
var sumOfLeftLeaves = function(root) {
    if (!root) return 0

    const postorder = (node, parent) => {
        if (!node) return 0
        if (!node.left && !node.right && parent.left == node) {
            return node.val
        }
        const leftLeavesSum = postorder(node.left, node)
        const rightLeavesSum = postorder(node.right, node)
        return leftLeavesSum + rightLeavesSum
    }

    return postorder(root, root)
}

// BFS
var sumOfLeftLeaves = function(root) {
    if (!root) return 0

    const isLeafNode = (node) => {
        return !node.left && !node.right
    }

    let sum = 0
    const queue = []
    queue.push(root)
    while (queue.length) {
        const size = queue.length
        for (let i = 0; i < size; i++) {
            const curr = queue.shift();
            if (curr.left) {
                if (isLeafNode(curr.left)) sum += curr.left.val
                else queue.push(curr.left)
            }
            if (curr.right && !isLeafNode(curr.right)) queue.push(curr.right)
        }
    }

    return sum
}