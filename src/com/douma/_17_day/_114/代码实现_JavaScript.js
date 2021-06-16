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
 * @return {void} Do not return anything, modify root in-place instead.
 */

// 前序遍历 + 串接节点
var flatten1 = function(root) {
    if (!root) return

    const res = []
    const dfs = (node) => {
        if (!node) return
        res.push(node)
        dfs(node.left)
        dfs(node.right)
    }

    dfs(root)
    let curr = root
    for (let i = 1; i < res.length; i++) {
        curr.left = null
        curr.right = res[i]
        curr = curr.right
    }
};


// 边遍历边串接
var flatten2 = function(root) {
    if (!root) return
    const stack = []
    stack.push(root)
    let prev = null
    while (stack.length) {
        const curr = stack.pop()
        if (prev) {
            prev.left = null
            prev.right = curr
        }
        if (curr.right) stack.push(curr.right)
        if (curr.left) stack.push(curr.left)
        prev = curr
    }
}


// 寻找前继节点
var flatten = function(root) {
    if (!root) return
    let curr = root
    while (curr) {
        if (curr.left) {
            const left = curr.left
            let pre = left
            while (pre.right) {
                pre = pre.right
            }
            pre.right = curr.right

            curr.left = null
            curr.right = left
        }
        curr = curr.right
    }
}