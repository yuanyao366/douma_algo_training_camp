/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
// 迭代
var buildTree = function(preorder, inorder) {
    const stack = []
    const root = new TreeNode(preorder[0])
    stack.push(root)
    let inorderIndex = 0
    for (let i = 1; i < preorder.length; i++) {
        const childVal = preorder[i]
        let parentNode =stack[stack.length - 1]
        if (parentNode.val != inorder[inorderIndex]) {
            parentNode.left = new TreeNode(childVal)
            stack.push(parentNode.left)
        } else {
            while (stack.length && stack[stack.length - 1].val == inorder[inorderIndex]) {
                parentNode = stack.pop()
                inorderIndex++
            }
            parentNode.right = new TreeNode(childVal)
            stack.push(parentNode.right)
        }
    }
    return root
};

// 递归
var buildTree = function(preorder, inorder) {
    const idxMap = new Map()
    for (let i = 0; i < inorder.length; i++) {
        idxMap.set(inorder[i], i)
    }
    let rootIndex = 0

    const buildTree = (left, right) => {
        if (left > right) return null

        const rootVal = preorder[rootIndex]
        const root = new TreeNode(rootVal)
        rootIndex++

        const mid = idxMap.get(rootVal)
        root.left = buildTree(left, mid - 1)
        root.right = buildTree(mid + 1, right)

        return root
    }

    return buildTree(0, inorder.length - 1)
}