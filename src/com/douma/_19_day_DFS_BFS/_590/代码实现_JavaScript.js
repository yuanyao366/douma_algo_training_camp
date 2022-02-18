/**
 * // Definition for a Node.
 * function Node(val,children) {
 *    this.val = val;
 *    this.children = children;
 * };
 */

/**
 * @param {Node} root
 * @return {number[]}
 */
// 迭代
var postorder1 = function(root) {
    if (!root) return []

    const res = [], stack = []
    stack.push(root)
    while (stack.length) {
        const curr = stack.pop();
        res.push(curr.val)
        for (const node of curr.children) {
            stack.push(node)
        }
    }
    res.reverse()
    return res
};

// 递归
var postorder = function(root) {

    const dfs = (node, res) => {
        if (!node) return
        for (const curr of node.children) {
            dfs(curr, res)
        }
        res.push(node.val)
    }

    const res = []
    dfs(root, res)
    return res
}