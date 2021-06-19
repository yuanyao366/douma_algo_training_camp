/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {TreeNode}
 */
// 1. 递归
var lowestCommonAncestor1 = function(root, p, q) {
    if (!root) return null
    if (root == p || root == q) return root

    const left = lowestCommonAncestor1(root.left, p, q)
    const right = lowestCommonAncestor1(root.right, p, q)

    if (!left) return right
    if (!right) return left
    return root
};

// 2. 迭代
var lowestCommonAncestor = function(root, p, q) {
    if (!root) return null
    if (root == p || root == q) return root
    let ancestor = root
    while (ancestor) {
        if (p.val > ancestor.val && q.val > ancestor.val) {
            ancestor = ancestor.right
        } else if (p.val < ancestor.val && q.val < ancestor.val) {
            ancestor = ancestor.left
        } else {
            break
        }
    }
    return ancestor
};