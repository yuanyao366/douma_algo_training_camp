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
// DFS
var countNodes = function(root) {
    if (!root) return 0

    const leftNodesCnt = countNodes(root.left)
    const rightNodesCnt = countNodes(root.right)

    return leftNodesCnt + rightNodesCnt + 1
};

// 二分
var countNodes = function(root) {
    if (!root) return 0

    let level = 0, curr = root
    while (curr.left) {
        level++
        curr = curr.left
    }

    const exists = (k) => {
        let mask = 1 << (level - 1)
        let node = root
        while (node && mask > 0) {
            if (mask & k) {
                node = node.right
            } else {
                node = node.left
            }
            mask >>= 1
        }
        return node != null
    }

    let low = 1 << level, high = (1 << (level + 1)) - 1
    while (low < high) {
        const mid = low + Math.floor((high - low + 1) / 2)
        if (exists(mid)) {
            low = mid
        } else {
            high = mid - 1
        }
    }

    return low
};