/**
 * // Definition for a Node.
 * function Node(val, left, right, next) {
 *    this.val = val === undefined ? null : val;
 *    this.left = left === undefined ? null : left;
 *    this.right = right === undefined ? null : right;
 *    this.next = next === undefined ? null : next;
 * };
 */

/**
 * @param {Node} root
 * @return {Node}
 */
// 1. BFS 层序遍历
var connect1 = function(root) {
    if (!root) return null
    let queue = []
    queue.push(root)
    while (queue.length) {
        const size = queue.length
        for (let i = 0; i < size; i++) {
            const curr = queue.shift()
            if (i != size - 1) curr.next = queue[0]
            if (curr.left) queue.push(curr.left)
            if (curr.right) queue.push(curr.right)
        }
    }
    return root
};

// 2. 双指针
var connect2 = function(root) {
    if (!root) return null
    let left = root
    while (left.left) {
        let curr = left
        while (curr) {
            curr.left.next = curr.right
            if (curr.next) curr.right.next = curr.next.left
            curr = curr.next
        }
        left = left.left
    }
    return root
}

// 3. DFS
var connect = function(root) {
    if (!root) return null

    const dfs = (node) => {
        if (!node) return

        let left = node.left, right = node.right
        while (left) {
            left.next = right
            left = left.right
            right = right.left
        }

        dfs(node.left)
        dfs(node.right)
    }

    dfs(root)
    return root
}