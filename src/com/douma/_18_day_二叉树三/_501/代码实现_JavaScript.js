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
 * @return {number[]}
 */
var findMode = function(root) {
    let count = 0, maxCount = 0, prevNum = 0
    let ans = []

    const update = (val) => {
        if (val == prevNum) {
            count++;
        } else {
            prevNum = val
            count = 1
        }
        if (count == maxCount) {
            ans.push(val)
        } else if (count > maxCount){
            maxCount = count
            ans = [val]
        }
    }

    const dfs = (node) => {
        if (!node) return
        dfs(node.left)
        update(node.val)
        dfs(node.right)
    }

    dfs(root)
    return ans
};