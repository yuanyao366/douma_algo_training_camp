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
// 时间复杂度：O(n)
var deleteNode1 = function(node) {
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

// 代码优化
// 时间复杂度：O(1)
var deleteNode = function(node) {
    // 实际上，我们只需要将下一个节点的值覆盖掉要删除的节点的值就可以
    node.val = node.next.val
    // 然后将下一个节点从链表中断开
    const next = node.next
    node.next = next.next
    next.next = null
};