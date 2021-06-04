/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} node
 * @return {void} Do not return anything, modify node in-place instead.
 */
var deleteNode = function(node) {
    let prev = node
    while (node) {
        const next = node.next
        if (next) {
            node.val = next.val
        } else {
            prev.next = null
        }
        prev = node
        node = node.next
    }
};