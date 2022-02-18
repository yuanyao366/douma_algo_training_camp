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
 * @param {number} val
 * @return {TreeNode}
 */
// 1，迭代
var insertIntoBST1 = function(root, val) {
    if (!root) return new TreeNode(val)

    let curr = root
    while (curr) {
        if (val < curr.val && !curr.left) {
            curr.left = new TreeNode(val)
            return root
        } else if (val > curr.val && !curr.right) {
            curr.right = new TreeNode(val)
            return root
        }

        if (val < curr.val) {
            curr = curr.left
        } else {
            curr = curr.right
        }
    }

    return root
};

// 2. 递归
var insertIntoBST = function(root, val) {
    if (!root) return new TreeNode(val)
    if (val < root.val) {
        root.left = insertIntoBST(root.left, val)
    } else {
        root.right = insertIntoBST(root.right, val)
    }
    return root
}