/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} nums
 * @return {TreeNode}
 */
// 思考：如果把数组变成链表的话，你会怎么实现呢？见 leetcode ：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
var sortedArrayToBST = function(nums) {
    const buildTree = (nums, left, right) => {
        if (left > right) return null
        const mid = left + Math.floor((right - left) / 2)
        const root = new TreeNode(nums[mid])
        root.left = buildTree(nums, left, mid - 1)
        root.right = buildTree(nums, mid + 1, right)
        return root
    }

    return buildTree(nums, 0, nums.length - 1)
};