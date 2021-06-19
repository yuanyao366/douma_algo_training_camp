/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} inorder
 * @param {number[]} postorder
 * @return {TreeNode}
 */
var buildTree = function(inorder, postorder) {
    const idxMap = new Map()
    for (let i = 0; i < inorder.length; i++) {
        idxMap.set(inorder[i], i)
    }
    let rootIndex = postorder.length - 1

    const buildTree = (left, right) => {
        if (left > right) return null

        const rootVal = postorder[rootIndex]
        const root = new TreeNode(rootVal)
        rootIndex--

        const mid = idxMap.get(rootVal)
        root.right = buildTree(mid + 1, right)
        root.left = buildTree(left, mid - 1)

        return root
    }

    return buildTree(0, inorder.length - 1)
};