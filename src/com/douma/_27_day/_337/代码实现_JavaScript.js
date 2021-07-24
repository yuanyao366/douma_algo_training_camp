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
var rob = function(root) {

    const dfs = (node) => {
        if (!node) return [0, 0]

        const left = dfs(node.left)
        const right = dfs(node.right)

        const res = new Array(2).fill(0)

        // 1. 选择不偷当前的节点，当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 2. 选择偷当前的节点：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数
        res[1] = left[0] + right[0] + node.val;

        return res;
    }

    const res = dfs(root)
    return Math.max(res[0], res[1])
};