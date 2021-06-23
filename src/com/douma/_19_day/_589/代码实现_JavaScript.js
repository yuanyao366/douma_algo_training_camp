/**
 * // Definition for a Node.
 * function Node(val, children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {Node} root
 * @return {number[]}
 */
// 迭代
var preorder1 = function(root) {
    if (!root) return []
    const stack = [], res = []
    stack.push(root)
    while (stack.length) {
        const curr = stack.pop()
        res.push(curr.val)
        for (let i = curr.children.length - 1; i >= 0; i--) {
            stack.push(curr.children[i])
        }
    }
    return res
};

// 递归
var preorder = function(root) {
    if (!root) return []

    const dfs = (node, res) => {
        if (!node) return
        res.push(node.val)
        for (let i = 0; i < node.children.length; i++) {
            dfs(node.children[i], res)
        }
    }

    const res = []
    dfs(root, res)
    return res
}