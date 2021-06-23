/**
 * // Definition for a Node.
 * function Node(val,children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {Node|null} root
 * @return {number[][]}
 */
// 迭代 BFS
var levelOrder = function(root) {
    if (!root) return []

    const res = [], queue = []
    queue.push(root)
    while (queue.length) {
        const size = queue.length
        const levelNodes = []
        for (let i = 0; i < size; i++) {
            const curr = queue.shift()
            levelNodes.push(curr.val)
            for (const node of curr.children) {
                queue.push(node)
            }
        }
        res.push(levelNodes)
    }

    return res
};

// 递归 DFS
var levelOrder = function(root) {
    if (!root) return []

    const dfs = (node, currLevel, res) => {
        if (!node) return
        if (res.length == currLevel) {
            res.push([node.val])
        } else {
            res[currLevel].push(node.val)
        }
        for (const curr of node.children) {
            dfs(curr, currLevel + 1, res)
        }
    }

    const res = []
    dfs(root, 0, res)
    return res
}