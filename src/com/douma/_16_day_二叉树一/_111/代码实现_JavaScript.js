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
// BFS
var minDepth1 = function(root) {
    if (!root) return 0

    let res = 0
    const queue = []
    queue.push(root)
    while (queue.length) {
        const size = queue.length
        res++
        for (let i = 0; i < size; i++) {
            const curr = queue.shift();
            if (!curr.left && !curr.right) return res
            if (curr.left) queue.push(curr.left)
            if (curr.right) queue.push(curr.right)
        }
    }
    return res
};

// DFS 前序遍历
var minDepth2 = function(root) {
    if (!root) return 0
    let res = Math.pow(2, 31) - 1
    const stack = []
    stack.push([root, 1])
    while (stack.length) {
        const curr = stack.pop()
        const currNode = curr[0], currLevel = curr[1]
        if (!currNode.left && !currNode.right) res = Math.min(res, currLevel)
        if (currNode.right) stack.push([currNode.right, currLevel + 1])
        if (currNode.left) stack.push([currNode.left, currLevel + 1])
    }
    return res
}

// DFS 前序遍历 递归
var minDepth3 = function(root) {
    if (!root) return 0
    let res = Math.pow(2, 31) - 1
    const preorder = (node, currLevel) => {
        if (!node) return
        if (!node.left && !node.right) res = Math.min(res, currLevel)
        preorder(node.left, currLevel + 1)
        preorder(node.right, currLevel + 1)
    }

    preorder(root, 1)
    return res
}

// DFS 后序遍历 递归
var minDepth = function(root) {
    if (!root) return 0

    const leftMinDepth = minDepth(root.left)
    const rightMinDepth = minDepth(root.right)

    if (!root.left) {
        return rightMinDepth + 1
    } else if (!root.right) {
        return leftMinDepth + 1
    }
    return Math.min(leftMinDepth, rightMinDepth) + 1
}